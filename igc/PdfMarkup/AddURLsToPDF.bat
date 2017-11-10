: Set input and output pdfs
SET input_pdf=C:\temp\GDPR\ST_5419_2016_INIT_EN.pdf
SET output_pdf=C:\temp\GDPR\ST_5419_2016_INIT_EN_Linked.pdf
: IGC settings
SET igc_category_rid=6662c0f2.ee6a64fe.cc8lp3485.eh13nr0.t1023d.aba4mok47hj7sitcnmrhc
SET igc_host_port=gdpr.mulvm.ie.ibm.com:443
SET igc_username=isadmin
SET igc_password=#######
: CSV settings
SET csv_file=C:\temp\GDPR\TermExMultiWord.csv
: use this command for calling via IGC
java -jar AddURLsToPDF.jar IGC %input_pdf% %output_pdf% %igc_category_rid% %igc_host_port% %igc_username% %igc_password%
: use this command for calling via a CSV, uncomment the line below and comment out the line above
: java -jar AddURLsToPDF.jar CSV %input_pdf% %output_pdf% %csv_file%

pause