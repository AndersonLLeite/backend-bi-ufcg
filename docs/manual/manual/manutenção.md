# 🔧 Manutenção e Atualização dos dados

# Atualização dos Dados no Power BI

A manutenção do *Bi Acadêmico UFCG* inclui a atualização periódica dos dados exibidos no relatório. Isso garante que as análises reflitam as informações mais recentes da UFCG. O processo a seguir descreve como atualizar os dados usando o Power BI Desktop e republicar o relatório no Power BI Service. 

### Passo a Passo para Atualização dos Dados:

- **Passo 1: Instalar o Power BI Desktop**
    
    Para atualizar os dados, você precisa do Power BI Desktop instalado no seu computador. Baixe e instale a versão mais recente em [powerbi.microsoft.com/desktop.](https://www.microsoft.com/pt-br/power-platform/products/power-bi/desktop)
    
- **Passo 2: Abrir o Arquivo do Power BI Service**
    - Acesse [app.powerbi.com](https://app.powerbi.com/) e faça login na conta.
    - No menu à esquerda, clique em **'Meu Workspace'** (ou o workspace onde o relatório está).
    - Localize o relatório Bi Acadêmico UFCG, clique nos três pontinhos (...) ao lado dele e selecione **'Baixar este arquivo'** (Download this file).
    - Abra o Power BI Desktop, clique em **'Arquivo'** > **'Abrir'** (ou Ctrl+O), selecione o arquivo baixado e clique em 'Abrir'.
- **Passo 3: Verificar URL da API**
    1. No Power BI Desktop, vá até a aba **'Página Inicial'** (Home) na barra superior.
    2. No menu superior clique em “**transformar dados**”.
    3. Na lista a esquerda junto com as tabelas de dados identifique o parâmetro URLBase e verifique se ele está em conformidade com a URL do serviço backend (Bi-Eureca-Acadêmico) Se não estiver atualize para a URL base correta.
    4. no menu superior a esquerda clique em “**fechar e aplicar**”
- **Passo 4: Atualizar os Dados**
    
    Os dados do relatório são buscados automaticamente via API:
    
    1. No Power BI Desktop, vá até a aba **'Página Inicial'** (Home) na barra superior.
    2. Clique em **'Atualizar'** (Refresh). O Power BI se conectará à API configurada no relatório e baixará os dados mais recentes.
    3. Aguarde o processo de atualização. Uma barra de progresso mostrará o status.
    4. Após a conclusão, verifique os visuais no relatório para confirmar que os dados foram atualizados (ex.: compare com informações recentes conhecidas).
- **Passo 5: Republicar no Power BI Service**
    1. No Power BI Desktop, clique em **'Publicar'** na aba **'Página Inicial'**.
    2. Selecione o workspace original (ex.: 'Meu Workspace') e clique em 'Selecionar'.
    3. Aguarde o upload. Uma mensagem confirmará que o relatório foi publicado.
    4. No Power BI Service, acesse o relatório e confirme que os dados estão atualizados.
    5. O link público existente (se já publicado na web) será atualizado automaticamente com os novos dados.