public class NewtonRaphson{
	public static void main(String [] args){
static double D_0 = 1000, D_1 = 1200, D_2 = 100, D_3 = 0, D_4 = 1100, D_5 = 0, D_6 = 900, D_7 = 0, D_8 = 0, D_9 = 0, Saldo = 5000;
static double t_0 = 3, t_1 = 4, t_2 = 5, t_3 = 6, t_4 = 7, t_5 = 8, t_6 = 9, t_7 = 10, t_8 = 11, t_9 = 12, t_S = 12;
double epsilon = 0.001;
double juros[];
juros[0] = 0.5;
int k = 0;
static double newton(double epsilon){
	for(k;juros[k]-juros[k+1]>=epsilon;k++){
		juros[k+1] = juros[k] - ((D_0 * pow(1 + juros[k], t_S - t_0) +
		D_1 * pow(1 + juros[k],t_S - t_1) +
		D_2 * pow(1 + juros[k],t_S - t_2) +
		D_3 * pow(1 + juros[k],t_S - t_3) +
		D_4 * pow(1 + juros[k],t_S - t_4) +
		D_5 * pow(1 + juros[k],t_S - t_5) +
		D_6 * pow(1 + juros[k],t_S - t_6) +
		D_7 * pow(1 + juros[k],t_S - t_7) +
		D_8 * pow(1 + juros[k],t_S - t_8) +
		D_9 * pow(1 + juros[k],t_S - t_9)) /
		((t_S - t_0) * D_0 * pow(1 + juros[k], t_S - t_0 - 1) +
		(t_S - t_1) * D_1 * pow(1 + juros[k], t_S - t_1 - 1) +
		(t_S - t_2) * D_2 * pow(1 + juros[k], t_S - t_2 - 1) +
		(t_S - t_3) * D_3 * pow(1 + juros[k], t_S - t_3 - 1) +
		(t_S - t_4) * D_4 * pow(1 + juros[k], t_S - t_4 - 1) +
		(t_S - t_5) * D_5 * pow(1 + juros[k], t_S - t_5 - 1) +
		(t_S - t_6) * D_6 * pow(1 + juros[k], t_S - t_6 - 1) +
		(t_S - t_7) * D_7 * pow(1 + juros[k], t_S - t_7 - 1) +
		(t_S - t_8) * D_8 * pow(1 + juros[k], t_S - t_8 - 1) +
		(t_S - t_9) * D_9 * pow(1 + juros[k], t_S - t_9 - 1)));
		if(juros[k]-juros[k+1]<epsilon)
			printf("O juros e de: "+juros[k+1]);
			}
		}
	}
}