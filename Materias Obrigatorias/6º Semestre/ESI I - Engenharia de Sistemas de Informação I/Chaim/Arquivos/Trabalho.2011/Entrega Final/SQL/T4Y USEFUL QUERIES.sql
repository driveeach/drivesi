SELECT idpassageiro, dataentrada
FROM utiliza
WHERE idpassageiro = 2 AND tarifa = TRUE AND TIMESTAMP '2011-10-29 12:14:15' - INTERVAL '2 hours' < dataentrada