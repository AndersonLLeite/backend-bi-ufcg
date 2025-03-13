[⬅️ Voltar](../index.md)

# ℹ️ Sobre

## 📋 Visão Geral

Este projeto foi criado para automatizar a atualização de dados e facilitar a visualização de informações por meio de um relatório no Power BI. Ele é composto por dois elementos principais:

1. **Backend**: Responsável por sincronizar, tratar e fornecer os dados atualizados.
2. **Relatório Power BI**: Consome os dados processados pelo backend e exibe informações de forma visual e interativa.

O objetivo é oferecer uma solução eficiente para manter bases de dados atualizadas e disponibilizar relatórios dinâmicos com o mínimo de intervenção manual.

---

## 🛠️ Estrutura do Projeto

### Backend

O backend é o coração da solução, responsável por gerenciar os dados. Ele foi desenvolvido para:

- **Sincronizar dados**: Consome informações de fontes externas (como APIs).
- **Tratar dados**: Processa e organiza as informações para uso no Power BI.
- **Fornecer endpoints**: Disponibiliza uma API local ou hospedada para o Power BI acessar os dados.

### Detalhes Técnicos

- **Repositório**: O código está disponível no GitHub: [https://github.com/AndersonLLeite/backend-bi-ufcg](https://github.com/AndersonLLeite/backend-bi-ufcg).
- **Execução**: Pode ser rodado localmente ou hospedado em um servidor.
    - **Local**: Permite sincronização manual e acesso à API para atualização do relatório.(Requer mais trabalho manual pois necessita rodar o backend localmente)
    - **Servidor**: Programado para atualizações automáticas e periódicas da base de dados.
- **API Externa**: Atualmente, consome os endpoints da API do Eureca na URL: [https://eureca.sti.ufcg.edu.br/das/v2/](https://eureca.sti.ufcg.edu.br/das/v2/).*Nota*: Se a URL mudar, é necessário atualizar o arquivo application.properties com a nova URL base.

### Relatório Power BI

O relatório no Power BI é a interface visual do projeto, permitindo a análise dos dados processados pelo backend. Ele:

- **Consome a API do backend**: Faz requisições para obter os dados mais recentes.
- **Exibe informações**: Apresenta os dados em gráficos, tabelas e outros elementos visuais interativos.
- **Atualização**: Deve ser atualizado manualmente. há uma seção nesse manual explicando como fazer isso.

---

## 🚀 Como Funciona?

1. **Sincronização**: O backend busca os dados da API do Eureca (ou outra fonte configurada).
2. **Processamento**: Os dados são tratados e armazenados de forma estruturada.
3. **Disponibilização**: O backend expõe uma API com os dados prontos.
4. **Visualização**: O Power BI consome essa API e atualiza o relatório automaticamente.

---

## 🌟 Benefícios

- **Automatização**: Reduz a necessidade de intervenção manual na atualização de dados.
- **Visualização Poderosa**: O Power BI transforma os dados em relatórios interativos e fáceis de entender.
- **Escalabilidade**: Pode ser adaptado para outras fontes de dados ou relatórios.
