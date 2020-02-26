--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.4
-- Dumped by pg_dump version 9.0.4
-- Started on 2011-10-31 10:05:45

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1865 (class 1262 OID 16904)
-- Name: Transport4You; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE "Transport4You" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';


\connect "Transport4You"

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 331 (class 2612 OID 11574)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: -
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


SET search_path = public, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 1524 (class 1259 OID 16907)
-- Dependencies: 5
-- Name: endereco; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE endereco (
    idendereco integer NOT NULL,
    rua character varying(50) NOT NULL,
    numend integer NOT NULL,
    bairro character varying(50) NOT NULL,
    cidade character varying(50) NOT NULL,
    estado character varying(2) NOT NULL,
    cep character varying(8) NOT NULL
);


--
-- TOC entry 1523 (class 1259 OID 16905)
-- Dependencies: 1524 5
-- Name: endereco_idendereco_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE endereco_idendereco_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1867 (class 0 OID 0)
-- Dependencies: 1523
-- Name: endereco_idendereco_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE endereco_idendereco_seq OWNED BY endereco.idendereco;


--
-- TOC entry 1868 (class 0 OID 0)
-- Dependencies: 1523
-- Name: endereco_idendereco_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('endereco_idendereco_seq', 7, true);


--
-- TOC entry 1537 (class 1259 OID 16986)
-- Dependencies: 5
-- Name: itinerario; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE itinerario (
    idlinha integer NOT NULL,
    idparada integer NOT NULL
);


--
-- TOC entry 1528 (class 1259 OID 16928)
-- Dependencies: 5
-- Name: linha; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE linha (
    idlinha integer NOT NULL,
    nome character(50) NOT NULL
);


--
-- TOC entry 1527 (class 1259 OID 16926)
-- Dependencies: 1528 5
-- Name: linha_idlinha_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE linha_idlinha_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1869 (class 0 OID 0)
-- Dependencies: 1527
-- Name: linha_idlinha_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE linha_idlinha_seq OWNED BY linha.idlinha;


--
-- TOC entry 1870 (class 0 OID 0)
-- Dependencies: 1527
-- Name: linha_idlinha_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('linha_idlinha_seq', 1, false);


--
-- TOC entry 1526 (class 1259 OID 16915)
-- Dependencies: 1819 5
-- Name: parada; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE parada (
    idparada integer NOT NULL,
    idendereco integer NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    sentido integer DEFAULT 0
);


--
-- TOC entry 1525 (class 1259 OID 16913)
-- Dependencies: 1526 5
-- Name: parada_idparada_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE parada_idparada_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1871 (class 0 OID 0)
-- Dependencies: 1525
-- Name: parada_idparada_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE parada_idparada_seq OWNED BY parada.idparada;


--
-- TOC entry 1872 (class 0 OID 0)
-- Dependencies: 1525
-- Name: parada_idparada_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('parada_idparada_seq', 13, true);


--
-- TOC entry 1536 (class 1259 OID 16965)
-- Dependencies: 5
-- Name: passageiro; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE passageiro (
    idpassageiro integer NOT NULL,
    idendereco integer NOT NULL,
    idtipo integer NOT NULL,
    idtelefone integer NOT NULL,
    nome character varying(100) NOT NULL,
    rg character varying(9) NOT NULL,
    cpf character varying(11) NOT NULL,
    saldo double precision NOT NULL,
    email character varying(100) NOT NULL,
    senha character varying(8) NOT NULL
);


--
-- TOC entry 1535 (class 1259 OID 16963)
-- Dependencies: 1536 5
-- Name: passageiro_idpassageiro_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE passageiro_idpassageiro_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1873 (class 0 OID 0)
-- Dependencies: 1535
-- Name: passageiro_idpassageiro_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE passageiro_idpassageiro_seq OWNED BY passageiro.idpassageiro;


--
-- TOC entry 1874 (class 0 OID 0)
-- Dependencies: 1535
-- Name: passageiro_idpassageiro_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('passageiro_idpassageiro_seq', 4, true);


--
-- TOC entry 1534 (class 1259 OID 16957)
-- Dependencies: 5
-- Name: telefone; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE telefone (
    idtelefone integer NOT NULL,
    idbluetooth character varying(12) NOT NULL,
    ddd integer NOT NULL,
    numero character varying(8) NOT NULL
);


--
-- TOC entry 1533 (class 1259 OID 16955)
-- Dependencies: 5 1534
-- Name: telefone_idtelefone_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE telefone_idtelefone_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1875 (class 0 OID 0)
-- Dependencies: 1533
-- Name: telefone_idtelefone_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE telefone_idtelefone_seq OWNED BY telefone.idtelefone;


--
-- TOC entry 1876 (class 0 OID 0)
-- Dependencies: 1533
-- Name: telefone_idtelefone_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('telefone_idtelefone_seq', 5, true);


--
-- TOC entry 1532 (class 1259 OID 16949)
-- Dependencies: 5
-- Name: tipo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE tipo (
    idtipo integer NOT NULL,
    nome character varying(20) NOT NULL
);


--
-- TOC entry 1531 (class 1259 OID 16947)
-- Dependencies: 1532 5
-- Name: tipo_idtipo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE tipo_idtipo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1877 (class 0 OID 0)
-- Dependencies: 1531
-- Name: tipo_idtipo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE tipo_idtipo_seq OWNED BY tipo.idtipo;


--
-- TOC entry 1878 (class 0 OID 0)
-- Dependencies: 1531
-- Name: tipo_idtipo_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('tipo_idtipo_seq', 1, false);


--
-- TOC entry 1539 (class 1259 OID 17001)
-- Dependencies: 1826 1827 5
-- Name: utiliza; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE utiliza (
    idutiliza integer NOT NULL,
    idpassageiro integer NOT NULL,
    idveiculo integer NOT NULL,
    tarifa boolean DEFAULT false NOT NULL,
    datahora timestamp without time zone,
    idparada integer,
    tipoutiliza integer DEFAULT 0
);


--
-- TOC entry 1538 (class 1259 OID 16999)
-- Dependencies: 1539 5
-- Name: utiliza_idutiliza_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE utiliza_idutiliza_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1879 (class 0 OID 0)
-- Dependencies: 1538
-- Name: utiliza_idutiliza_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE utiliza_idutiliza_seq OWNED BY utiliza.idutiliza;


--
-- TOC entry 1880 (class 0 OID 0)
-- Dependencies: 1538
-- Name: utiliza_idutiliza_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('utiliza_idutiliza_seq', 23, true);


--
-- TOC entry 1530 (class 1259 OID 16936)
-- Dependencies: 5
-- Name: veiculo; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE veiculo (
    idveiculo integer NOT NULL,
    idlinha integer,
    codigobt character varying(12)
);


--
-- TOC entry 1529 (class 1259 OID 16934)
-- Dependencies: 5 1530
-- Name: veiculo_idveiculo_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE veiculo_idveiculo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 1881 (class 0 OID 0)
-- Dependencies: 1529
-- Name: veiculo_idveiculo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE veiculo_idveiculo_seq OWNED BY veiculo.idveiculo;


--
-- TOC entry 1882 (class 0 OID 0)
-- Dependencies: 1529
-- Name: veiculo_idveiculo_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('veiculo_idveiculo_seq', 2, true);


--
-- TOC entry 1817 (class 2604 OID 16910)
-- Dependencies: 1523 1524 1524
-- Name: idendereco; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE endereco ALTER COLUMN idendereco SET DEFAULT nextval('endereco_idendereco_seq'::regclass);


--
-- TOC entry 1820 (class 2604 OID 16931)
-- Dependencies: 1527 1528 1528
-- Name: idlinha; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE linha ALTER COLUMN idlinha SET DEFAULT nextval('linha_idlinha_seq'::regclass);


--
-- TOC entry 1818 (class 2604 OID 16918)
-- Dependencies: 1525 1526 1526
-- Name: idparada; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE parada ALTER COLUMN idparada SET DEFAULT nextval('parada_idparada_seq'::regclass);


--
-- TOC entry 1824 (class 2604 OID 16968)
-- Dependencies: 1536 1535 1536
-- Name: idpassageiro; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE passageiro ALTER COLUMN idpassageiro SET DEFAULT nextval('passageiro_idpassageiro_seq'::regclass);


--
-- TOC entry 1823 (class 2604 OID 16960)
-- Dependencies: 1534 1533 1534
-- Name: idtelefone; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE telefone ALTER COLUMN idtelefone SET DEFAULT nextval('telefone_idtelefone_seq'::regclass);


--
-- TOC entry 1822 (class 2604 OID 16952)
-- Dependencies: 1531 1532 1532
-- Name: idtipo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE tipo ALTER COLUMN idtipo SET DEFAULT nextval('tipo_idtipo_seq'::regclass);


--
-- TOC entry 1825 (class 2604 OID 17004)
-- Dependencies: 1538 1539 1539
-- Name: idutiliza; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE utiliza ALTER COLUMN idutiliza SET DEFAULT nextval('utiliza_idutiliza_seq'::regclass);


--
-- TOC entry 1821 (class 2604 OID 16939)
-- Dependencies: 1529 1530 1530
-- Name: idveiculo; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE veiculo ALTER COLUMN idveiculo SET DEFAULT nextval('veiculo_idveiculo_seq'::regclass);


--
-- TOC entry 1854 (class 0 OID 16907)
-- Dependencies: 1524
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: -
--

COPY endereco (idendereco, rua, numend, bairro, cidade, estado, cep) FROM stdin;
4	Av. Brás Leme	1000	Santana	São Paulo	SP	02022130
5	Av. Brás Leme	1020	Santana	São Paulo	SP	02022130
1	Rua Vasco Cinquini	70	Vila Bianca	São Paulo	SP	02022130
3	Rua Vasco Cinquini	70	Vila Bianca	São Paulo	SP	02022130
2	Rua Vasco Cinquini	70	Vila Bianca	São Paulo	SP	02022130
6	Av. Brás Leme	1040	Santana	São Paulo	SP	02022130
7	Av. Brás Leme	1060	Santana	São Paulo	SP	02022130
\.


--
-- TOC entry 1861 (class 0 OID 16986)
-- Dependencies: 1537
-- Data for Name: itinerario; Type: TABLE DATA; Schema: public; Owner: -
--

COPY itinerario (idlinha, idparada) FROM stdin;
\.


--
-- TOC entry 1856 (class 0 OID 16928)
-- Dependencies: 1528
-- Data for Name: linha; Type: TABLE DATA; Schema: public; Owner: -
--

COPY linha (idlinha, nome) FROM stdin;
1	175T - Santana/Jabaquara                          
2	Brás/USPLeste                                     
\.


--
-- TOC entry 1855 (class 0 OID 16915)
-- Dependencies: 1526
-- Data for Name: parada; Type: TABLE DATA; Schema: public; Owner: -
--

COPY parada (idparada, idendereco, latitude, longitude, sentido) FROM stdin;
1	1	10	10	0
8	5	1	1	0
9	6	2	2	0
10	7	3	3	0
7	4	0	0	0
\.


--
-- TOC entry 1860 (class 0 OID 16965)
-- Dependencies: 1536
-- Data for Name: passageiro; Type: TABLE DATA; Schema: public; Owner: -
--

COPY passageiro (idpassageiro, idendereco, idtipo, idtelefone, nome, rg, cpf, saldo, email, senha) FROM stdin;
3	2	2	5	Thais Costa	45508810X	31679187805	6	thais_thac@yahoo.com.br	12341234
2	2	2	1	Murilo Galvão Honorio	383153293	30922222886	4.5	murilo.honorio@gmail.com	12341234
\.


--
-- TOC entry 1859 (class 0 OID 16957)
-- Dependencies: 1534
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: -
--

COPY telefone (idtelefone, idbluetooth, ddd, numero) FROM stdin;
1	001DFDE6758A	11	99661800
5	001EDCF672B2	11	99667673
\.


--
-- TOC entry 1858 (class 0 OID 16949)
-- Dependencies: 1532
-- Data for Name: tipo; Type: TABLE DATA; Schema: public; Owner: -
--

COPY tipo (idtipo, nome) FROM stdin;
1	Comum
2	Estudante
3	Especial
4	Administrador
\.


--
-- TOC entry 1862 (class 0 OID 17001)
-- Dependencies: 1539
-- Data for Name: utiliza; Type: TABLE DATA; Schema: public; Owner: -
--

COPY utiliza (idutiliza, idpassageiro, idveiculo, tarifa, datahora, idparada, tipoutiliza) FROM stdin;
15	2	1	f	2011-10-30 18:31:30	8	1
22	2	1	f	2011-10-30 20:00:04	10	1
23	2	1	t	2011-10-30 20:00:04	10	0
20	3	1	f	2011-10-30 19:52:03	8	1
16	3	1	f	2011-10-30 19:20:32	8	1
18	2	2	f	2011-10-30 19:30:45	8	1
14	2	2	t	2011-10-30 16:30:09	7	0
\.


--
-- TOC entry 1857 (class 0 OID 16936)
-- Dependencies: 1530
-- Data for Name: veiculo; Type: TABLE DATA; Schema: public; Owner: -
--

COPY veiculo (idveiculo, idlinha, codigobt) FROM stdin;
1	1	000DF06ECE17
2	2	FFFDF06ECE17
\.


--
-- TOC entry 1829 (class 2606 OID 16912)
-- Dependencies: 1524 1524
-- Name: pk_endereco; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY endereco
    ADD CONSTRAINT pk_endereco PRIMARY KEY (idendereco);


--
-- TOC entry 1833 (class 2606 OID 16933)
-- Dependencies: 1528 1528
-- Name: pk_linha; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY linha
    ADD CONSTRAINT pk_linha PRIMARY KEY (idlinha);


--
-- TOC entry 1831 (class 2606 OID 16920)
-- Dependencies: 1526 1526
-- Name: pk_parada; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parada
    ADD CONSTRAINT pk_parada PRIMARY KEY (idparada);


--
-- TOC entry 1841 (class 2606 OID 16970)
-- Dependencies: 1536 1536
-- Name: pk_passageiro; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT pk_passageiro PRIMARY KEY (idpassageiro);


--
-- TOC entry 1839 (class 2606 OID 16962)
-- Dependencies: 1534 1534
-- Name: pk_telefone; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY telefone
    ADD CONSTRAINT pk_telefone PRIMARY KEY (idtelefone);


--
-- TOC entry 1837 (class 2606 OID 16954)
-- Dependencies: 1532 1532
-- Name: pk_tipo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY tipo
    ADD CONSTRAINT pk_tipo PRIMARY KEY (idtipo);


--
-- TOC entry 1843 (class 2606 OID 17006)
-- Dependencies: 1539 1539
-- Name: pk_utiliza; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utiliza
    ADD CONSTRAINT pk_utiliza PRIMARY KEY (idutiliza);


--
-- TOC entry 1835 (class 2606 OID 16941)
-- Dependencies: 1530 1530
-- Name: pk_veiculo; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT pk_veiculo PRIMARY KEY (idveiculo);


--
-- TOC entry 1849 (class 2606 OID 16989)
-- Dependencies: 1528 1832 1537
-- Name: fk_itinerario_linha; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY itinerario
    ADD CONSTRAINT fk_itinerario_linha FOREIGN KEY (idlinha) REFERENCES linha(idlinha);


--
-- TOC entry 1850 (class 2606 OID 16994)
-- Dependencies: 1526 1830 1537
-- Name: fk_itinerario_parada; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY itinerario
    ADD CONSTRAINT fk_itinerario_parada FOREIGN KEY (idparada) REFERENCES parada(idparada);


--
-- TOC entry 1844 (class 2606 OID 16921)
-- Dependencies: 1526 1828 1524
-- Name: fk_parada_endereco; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY parada
    ADD CONSTRAINT fk_parada_endereco FOREIGN KEY (idendereco) REFERENCES endereco(idendereco);


--
-- TOC entry 1846 (class 2606 OID 16971)
-- Dependencies: 1536 1828 1524
-- Name: fk_passageiro_endereco; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT fk_passageiro_endereco FOREIGN KEY (idendereco) REFERENCES endereco(idendereco);


--
-- TOC entry 1848 (class 2606 OID 16981)
-- Dependencies: 1838 1534 1536
-- Name: fk_passageiro_telefone; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT fk_passageiro_telefone FOREIGN KEY (idtelefone) REFERENCES telefone(idtelefone);


--
-- TOC entry 1847 (class 2606 OID 16976)
-- Dependencies: 1836 1536 1532
-- Name: fk_passageiro_tipo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passageiro
    ADD CONSTRAINT fk_passageiro_tipo FOREIGN KEY (idtipo) REFERENCES tipo(idtipo);


--
-- TOC entry 1853 (class 2606 OID 17017)
-- Dependencies: 1539 1830 1526
-- Name: fk_utiliza_parada_entrada; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utiliza
    ADD CONSTRAINT fk_utiliza_parada_entrada FOREIGN KEY (idparada) REFERENCES parada(idparada);


--
-- TOC entry 1851 (class 2606 OID 17007)
-- Dependencies: 1536 1539 1840
-- Name: fk_utiliza_passageiro; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utiliza
    ADD CONSTRAINT fk_utiliza_passageiro FOREIGN KEY (idpassageiro) REFERENCES passageiro(idpassageiro);


--
-- TOC entry 1852 (class 2606 OID 17012)
-- Dependencies: 1530 1834 1539
-- Name: fk_utiliza_veiculo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY utiliza
    ADD CONSTRAINT fk_utiliza_veiculo FOREIGN KEY (idveiculo) REFERENCES veiculo(idveiculo);


--
-- TOC entry 1845 (class 2606 OID 16942)
-- Dependencies: 1832 1530 1528
-- Name: fk_veiculo_linha; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY veiculo
    ADD CONSTRAINT fk_veiculo_linha FOREIGN KEY (idlinha) REFERENCES linha(idlinha);


-- Completed on 2011-10-31 10:05:47

--
-- PostgreSQL database dump complete
--

