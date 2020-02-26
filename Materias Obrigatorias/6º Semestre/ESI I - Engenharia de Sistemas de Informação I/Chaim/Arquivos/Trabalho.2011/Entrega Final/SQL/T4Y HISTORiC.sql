SELECT idveiculo, tarifa, datahora, idparada, tipoutiliza
FROM utiliza
WHERE idpassageiro=2
ORDER BY datahora;

SELECT rua, numend, bairro, cep
FROM parada AS p JOIN endereco AS e ON p.idendereco = e.idendereco
WHERE idparada=8;

SELECT u.datahora, rua, numend, bairro, cep, l.nome
FROM veiculo AS v, linha AS l, utiliza AS u, parada AS p JOIN endereco AS e ON p.idendereco = e.idendereco
WHERE u.idparada=p.idparada AND u.idpassageiro = 2 AND u.idveiculo = v.idveiculo AND v.idlinha = l.idlinha
ORDER BY datahora