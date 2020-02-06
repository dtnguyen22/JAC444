
       dcl-f INCOMETDSP workstn;
       dcl-s DUEDATE DATE(*YMD) INZ(d'2020-04-30');
       dcl-s TODAY DATE(*YMD);


       EXFMT GETINCOME;
       DOW NOT(*IN03);                    // if F3 is not pressed
         IF INCOMEAMT=0;
            *IN98 = *ON;                //if income = zero; activate indicator 98, which will be
            EXFMT GETINCOME;            //showing 'zero ...'
            *IN98=*OFF;
            ITER;
         ELSEIF MARRIED = '';
             *IN97 = *ON;
             EXFMT GETINCOME;
             *IN97 = *OFF;
             ITER;
         ELSE;
                        //set TAXRATE
             EXSR GETDEDUCTIONS;
             EXSR GETDUEDATE;
             AINCOMEAMT = INCOMEAMT - DEDUCTIONS;
             EXSR GETTAXRATE;
             TAXAMT = TAXRATE * AINCOMEAMT;

         ENDIF;

         *IN60 = *ON;
         WRITE GETINCOME;
         EXFMT YOURTAX;

         IF *IN03 = *OFF;
           EXSR CLEAR;
           EXFMT GETINCOME;
         ENDIF;
       ENDDO;
       *INLR = *ON;
       RETURN;


       BEGSR GETDUEDATE;
         TODAY = %DATE();

         DAYSTOPAY = %DIFF(today: DUEDATE: *DAYS);
       ENDSR;

       BEGSR GETDEDUCTIONS;         //func that calculate deductions
         IF STDEXPENSE > 4000;
           STDEXPENSE = 4000;
         ENDIF;
         IF DONATIONS > 10000;
           DONATIONS = 10000;
         ENDIF;
         DEDUCTIONS = STDEXPENSE + DONATIONS;
       ENDSR;

       BEGSR GETTAXRATE;
         IF MARRIED = 'N';               //function that set taxrate based on income
           SELECT;
             when AINCOMEAMT <= 9525;
               taxrate = 0.1;
             when AINCOMEAMT <= 38700;
               taxrate = 0.12;
             when AINCOMEAMT <= 82500;
               taxrate = 0.22;
             when AINCOMEAMT <= 157500;
               taxrate = 0.24;
             when AINCOMEAMT <= 200000;
               taxrate = 0.32;
             when AINCOMEAMT <= 500000;
               taxrate = 0.35;
             other;
               taxrate = 0.37;
           ENDSL;
          else;
             SELECT;  // MARRIED = Y
               when AINCOMEAMT <= 19050;
                 taxrate = 0.1;
               when AINCOMEAMT <= 77400;
                 taxrate = 0.12;
               when AINCOMEAMT <= 165000;
                 taxrate = 0.22;
               when AINCOMEAMT <= 315000;
                 taxrate = 0.24;
               when AINCOMEAMT <= 400000;
                 taxrate = 0.32;
               when AINCOMEAMT <= 600000;
                 taxrate = 0.35;
               other;
                 taxrate = 0.37;
             ENDSL;
         ENDIF;

       ENDSR;

       BEGSR CLEAR;                    // clear fields and remove protection
         *IN60 = *OFF;
         TAXRATE = .0;
         MARRIED = '';
         INCOMEAMT = 0;
         STDEXPENSE = 0;
         DONATIONS = 0;
         DEDUCTIONS = 0;
         AINCOMEAMT = 0;
         TAXAMT = 0;

       ENDSR; 
