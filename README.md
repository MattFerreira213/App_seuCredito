# App_seuCredito

## Descrição
O app foi todo construído em android com a linguagem Java sem a utilização de um framework

## Levantar a aplicação
Após clonar o repositório, deverá abri-lo na IDE Android Studio

Para testar a aplicação pode ser utilizado um smartphone android ou criar uma Avd Manager. 
Sugiro a utilização do smartphone pois utiliza menos memória do computador e roda com mais facilidade além de ser basta para conectar. Basta conectar o smartphone ao computador via cabo USB.

OBS: O smartphone deve possuir no mínimo o android 5.0 (API 21)

Estará disponível um arquivo APK para instalar no smartphone.

## Regra de negócio
O cálculo do empréstimo foi realizado com base no cálculo de juros simples.
* J=C x i x t
* valorTotal = C + j

Para realizar o empréstimo a pessoa deve obrigatoriamente ter uma renda mensal maior ou igual a salário mínimo e ser maior de idade.
O valor máximo do empréstimo é de até 5x mais que a renda mensal da pessoa, ou seja, se ela tem uma renda mensal de 2500 reais o limite de crédito dela é de 12500 reais.
*Salário mínimo = R$1100

A taxa é estipulada de acordo com a quantidade de parcelas que a pessoa solicitar.
* Até 12x a taxa é de 1,2% ao mês
* Até 36x a taxa é de 1,8% ao mês
* Mais de 36x a taxa é de 2,4% ao mês

As opções de pagamento para parcelas maiores ou iguais a 3.
* X parcelas
* Primeira parcela 20% do valor total + X parcelas do valor restante
* Primeira parcela 25% do valor total + X parcelas do valor restante
* Primeira parcela 30% do valor total + X parcelas do valor restante

### IDE
* Android Studio
