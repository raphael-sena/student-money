# Histórias de Usuário

Histórias de Usuário do Sistema de Moeda Estudantil.

## Persona: Aluno

### História 1 – Cadastro de aluno
**Como** um aluno interessado em participar do sistema  
**Quero** me cadastrar informando meus dados pessoais e acadêmicos  
**Para que** eu possa receber moedas e utilizá-las nas vantagens do sistema

**Critérios de aceitação:**
- Informar nome, email, CPF, RG, endereço, instituição de ensino e curso
- A instituição deve estar pré-cadastrada e ser selecionada
- O aluno deve ter login e senha para acessar o sistema

---

### História 2 – Consultar saldo e extrato
**Como** aluno cadastrado  
**Quero** visualizar meu saldo de moedas e histórico de transações  
**Para que** eu acompanhe quanto recebi, usei e de onde vieram minhas moedas

**Critérios de aceitação:**
- Listar transações de recebimento e trocas
- Mostrar total de moedas disponíveis

---

### História 3 – Receber moedas
**Como** aluno reconhecido por um professor  
**Quero** receber moedas acompanhadas de uma mensagem  
**Para que** eu seja recompensado por bom desempenho

**Critérios de aceitação:**
- Receber email notificando o recebimento de moedas
- Visualizar a mensagem enviada pelo professor

---

### História 4 – Trocar moedas por produtos
**Como** aluno com saldo de moedas  
**Quero** trocar moedas por produtos oferecidas por parceiros  
**Para que** eu usufrua de benefícios como descontos e produtos

**Critérios de aceitação:**
- Selecionar vantagem desejada
- Descontar valor em moedas do saldo
- Receber email com código do cupom
- O parceiro também deve ser notificado por email

---

## Persona: Professor

### História 5 – Consulta de saldo e extrato
**Como** professor participante do sistema  
**Quero** ver meu saldo de moedas e o histórico de envios  
**Para que** eu controle as moedas que posso distribuir

**Critérios de aceitação:**
- Exibir saldo disponível
- Listar transações realizadas com detalhes do aluno e da mensagem enviada

---

### História 6 – Enviar moedas para aluno
**Como** professor com saldo disponível  
**Quero** enviar moedas para um aluno  
**Para que** eu reconheça seu bom desempenho

**Critérios de aceitação:**
- Selecionar aluno
- Especificar quantidade de moedas (respeitando saldo)
- Escrever uma mensagem obrigatória
- O aluno deve ser notificado por email

---

### História 7 – Acúmulo de moedas semestrais
**Como** professor ativo no sistema  
**Quero** receber automaticamente 1000 moedas a cada semestre  
**Para que** eu tenha novas moedas para distribuir, mantendo o saldo acumulado

**Critérios de aceitação:**
- O sistema deve somar 1000 moedas ao saldo no início de cada semestre

---

## Persona: Empresa Parceira

### História 8 – Cadastro de empresa parceira
**Como** empresa interessada em oferecer vantagens  
**Quero** me cadastrar no sistema  
**Para que** eu possa ofertar benefícios em troca de moedas

**Critérios de aceitação:**
- Informar nome, email, CNPJ e dados de login
- Ter login e senha para acessar o sistema

---

### História 9 – Cadastro de vantagens
**Como** empresa parceira cadastrada  
**Quero** registrar vantagens no sistema  
**Para que** alunos possam trocar moedas por meus produtos ou serviços

**Critérios de aceitação:**
- Informar nome da vantagem, custo em moedas, descrição e imagem
- As vantagens devem ser exibidas para os alunos

---

### História 10 – Notificação de resgate
**Como** empresa parceira  
**Quero** receber um email com o código do cupom resgatado  
**Para que** eu possa validar o uso do benefício pelo aluno

**Critérios de aceitação:**
- O email deve conter um código único do resgate
- Esse código deve ser compartilhado com o aluno e com a empresa

---

## Persona: Sistema (Administração)

### História 11 – Login e autenticação
**Como** qualquer usuário (aluno, professor, empresa)  
**Quero** fazer login com email e senha  
**Para que** eu possa acessar minha área restrita com segurança

**Critérios de aceitação:**
- Login com email e senha
- Verificação de credenciais válidas
- Redirecionamento para área correta conforme o tipo de usuário

