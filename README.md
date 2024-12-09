# URL-Shortener-AWS-Lambda-S3

### Descrição do Projeto
Este é um encurtador de URLs simples e eficiente, desenvolvido utilizando AWS Lambda e Amazon S3. O projeto permite criar URLs encurtadas com validade configurável e redirecionar para a URL original, validando a expiração.

### Arquitetura do Projeto

A solução utiliza uma arquitetura serverless, composta por:

AWS Lambda: Responsável por processar requisições de criação e redirecionamento de URLs.
Amazon S3: Armazena os dados das URLs encurtadas, incluindo o código, URL original e o tempo de expiração.
API Gateway: Gerencia as requisições HTTP para as funções Lambda.

### Funcionalidades

Criar uma URL encurtada com tempo de expiração definido pelo usuário.
Redirecionar para a URL original usando o código encurtado.
Retornar erro caso a URL tenha expirado.

### Pré-requisitos

Conta na AWS.
Configuração da AWS CLI com permissões para S3 e Lambda.
Java 11 ou superior.
Maven instalado para gerenciamento de dependências.

### Instalação

1. Clone o repositório:
    git clone https://github.com/seu-usuario/url-shortener.git
    cd url-shortener
2. Configure um bucket no S3
3. Compile o projeto com Maven:
    mvn clean package
4. Faça o upload das funções Lambda:
    CreateShortUrlLambda: Para criar URLs encurtadas.
    RedirectShortUrlLambda: Para redirecionar URLs encurtadas.
5. Configure as permissões das funções para ter acesso ao bucket criado.
6. Configure o API Gateway:
    Rota POST para /create chamando CreateShortUrlLambda.
    Rota GET para /{shortCode} chamando RedirectShortUrlLambda.

### Uso

#### 1. Criar uma URL encurtada

Envie uma requisição POST para a rota configurada:
curl -X POST https://sua-api-url/create \
  -H "Content-Type: application/json" \
  -d '{
    "originalUrl": "https://exemplo.com",
    "expirationTime": "3600"
  }'
Resposta esperada:
 {
  "code": "abcd1234"
 }
 
#### 2. Redirecionar para a URL original
Acesse a rota GET com o código gerado:
curl -X GET https://sua-api-url/abcd1234
Caso a URL esteja válida, haverá um redirecionamento (HTTP 302).
Caso esteja expirada, a resposta será:
{
  "statusCode": 410,
  "body": "This URL has expired"
}

### Estrutura do Projeto

#### Pacote createUrlShortner:
Main: Função para criar URLs encurtadas.
UrlData: Classe para armazenar dados da URL.

#### Pacote redirectUrlShortner:
Main: Função para redirecionar URLs.
UrlData: Classe para desserializar os dados armazenados no S3.

