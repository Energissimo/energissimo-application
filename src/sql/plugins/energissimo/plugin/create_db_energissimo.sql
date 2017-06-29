
--
-- Structure for table energissimo_cluster
--

DROP TABLE IF EXISTS energissimo_cluster;
CREATE TABLE energissimo_cluster (
id_cluster int(6) NOT NULL,
name varchar(50) default '' NOT NULL,
PRIMARY KEY (id_cluster)
);

--
-- Structure for table energissimo_suggestion
--

DROP TABLE IF EXISTS energissimo_suggestion;
CREATE TABLE energissimo_suggestion (
id_suggestion int(6) NOT NULL,
PRIMARY KEY (id_suggestion)
);


						