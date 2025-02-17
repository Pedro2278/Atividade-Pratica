# Controle de Volume Circular

Este projeto implementa um controle de volume circular utilizando Java e a biblioteca **Swing** para a interface gráfica. O controle de volume é representado por um botão giratório, permitindo uma experiência visualmente intuitiva e prática, onde o volume pode ser ajustado de 0% a 100% ao girar o ponteiro.

## Funcionalidades

- **Controle Circular**: O volume pode ser ajustado por meio de um ponteiro rotacionável, imitando o comportamento de um controle de volume tradicional.
- **Indicador de Volume**: A cada movimento do ponteiro, o valor atual do volume é mostrado como uma porcentagem de 0 a 100%.
- **Limite de Volume**: O ponteiro permite aumentar o volume até 100%, após o qual o ponteiro para de aumentar e só pode ser girado no sentido contrário (diminuindo o volume).
- **Interface Visual**: O design é simples e elegante, com o círculo de controle em **azul**, o ponteiro em **vermelho**, e o fundo da tela em **branco**.

## Tecnologias Utilizadas

- **Java 8+**
- **Swing** para a interface gráfica
- **Matemática para cálculo do ângulo** do ponteiro de volume

## Como Executar

1. Clone o repositório para o seu computador:

   ```bash
   git clone https://github.com/SeuUsuario/Controle-Volume-Circular.git
