DROP TABLE IF EXISTS testdata;
CREATE TABLE testdata(
                         region               VARCHAR(20) NOT NULL
    ,verbrauchAnteil decimal(20,4)    NOT NULL
    ,verbrauch       NUMERIC(20,4)    NOT NULL
    ,preis           BIGINT           NOT NULL
    ,emission        NUMERIC(20,4)    NOT NULL
    ,import          NUMERIC(20,4)    NOT NULL
    ,export          NUMERIC(20,4)    NOT NULL
    ,date            datetime         NOT NULL
    ,PRIMARY KEY(region,date)
);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Burgenland',0.029,32.8715,17,5.916,2.9,2.03,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Burgenland',0.036618887,45.6015,17,7.397015177,4.394266442,2.380227656,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Burgenland',0.037894583,48.5215,18,9.625224128,5.684187477,2.463147907,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Kaernten',0.075,85.0125,19,15.3,7.5,5.25,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Kaernten',0.073614792,91.6725,19,14.87018791,8.833774994,4.784961455,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Kaernten',0.074649692,95.5825,20,18.96102166,11.19745374,4.852229953,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Niederoesterreich',0.205,286.366,17,41.82,20.5,14.35,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Niederoesterreich',0.20009034,249.1725,18,40.41824861,24.01084076,13.00587208,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Niederoesterreich',0.199377704,255.2825,18,50.6419369,29.90665565,12.95955078,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Oberoesterreich',0.236,97.8525,16,48.144,23.6,16.52,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Oberoesterreich',0.22995744,286.366,17,46.45140287,27.5948928,14.9472336,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Oberoesterreich',0.227810174,291.686,19,57.86378429,34.17152616,14.80766133,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Salzburg',0.056,63.476,17,11.424,5.6,3.92,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Salzburg',0.054427849,67.779,17,10.99442544,6.531341845,3.537810166,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Salzburg',0.055356195,70.879,18,14.06047345,8.3034292,3.598152653,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Steiermark',0.171,193.8285,18,34.884,17.1,11.97,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Steiermark',0.170672529,212.5385,19,34.4758508,20.48070344,11.09371437,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Steiermark',0.170476957,218.2785,19,43.30114704,25.57154353,11.0810022,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Tirol',0.075,85.0125,16,15.3,7.5,5.25,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Tirol',0.078577451,97.8525,16,15.87264515,9.429294146,5.107534329,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Tirol',0.080186575,102.6725,17,20.3673901,12.02798628,5.212127387,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Vorarlberg',0.037,41.9395,14,7.548,3.7,2.59,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Vorarlberg',0.035603068,44.3365,15,7.191819642,4.272368104,2.31419939,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Vorarlberg',0.035103873,44.9465,15,8.916383783,5.265580974,2.281751755,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Wien',0.116,131.486,15,23.664,11.6,8.12,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Wien',0.120437646,149.981,17,24.3284044,14.45251747,7.828446961,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Wien',0.119144247,152.551,18,30.26263865,17.871637,7.744376032,2023-01-12 10:10:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Oesterreich',1,1133.5,16,204,100,70,2023-01-24 08:15:30);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Oesterreich',1,1245.3,17,202,120,65,2023-01-12 10:05:00);
INSERT INTO testdata(region,stromverbrauchanteil,stromverbrauch,strompreis,CO2Emissionen,Stromimport,Stromexport,date) VALUES ('Oesterreich',1,1280.4,18,254,150,65,2023-01-12 10:10:00);