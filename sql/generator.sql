DROP TABLE IF EXISTS estats;
CREATE TABLE estats(
                       region               VARCHAR(20) NOT NULL
    ,verbrauchAnteil      decimal(20,4) NOT NULL
    ,verbrauch            decimal(20,4) NOT NULL
    ,preis                BIGINT NOT NULL
    ,emission             decimal(20,4) NOT NULL
    ,import               decimal(20,4) NOT NULL
    ,export               decimal(20,4) NOT NULL
    ,date                 datetime  NOT NULL
    ,PRIMARY KEY(region,date)
);
CREATE TABLE voraussagen(
                            region               VARCHAR(20) NOT NULL
    ,vorverbrauch         decimal(20,4) NOT NULL
    ,vorpreis             BIGINT NOT NULL
    ,voremission          decimal(20,4) NOT NULL
    ,vorimport            decimal(20,4) NOT NULL
    ,vorexport            decimal(20,4) NOT NULL
    ,hour					INT NOT NULL
    ,PRIMARY KEY(hour)
);