# language: pt
# Author: Allan Caetano
# Version: 1.0
# encoding: UTF-8

Funcionalidade: Validar requisições para Simulação de Credito Sicredi verificando situação de restrição de CPF e
  criação de simulações de emprestimos para solicitação de pedidos de credito

  @cpf-com-restricao  @ok
  Cenario: Validar uma Consulta a um CPF com restricao
    Dado que possuo acesso a API de restricoes
    Quando envio um resquest de um CPF "58063164083" com dados validos
    Então devo receber a mensagem informando situacao do CPF "58063164083"
    E o status code deve ser 200

  @lista-cpf-com-restricao
  Esquema do Cenario: Simulador possui uma restrição
    Dado que possuo acesso a API de restricoes
    Quando eu efetuar uma solicitação para "<CPF restriçao>" recurso de restricoes
    Então o status code deve ser 200
    Exemplos:
      | CPF restriçao |
      | 97093236014   |
      | 60094146012   |
      | 84809766080   |
      | 62648716050   |
      | 26276298085   |
      | 01317496094   |
      | 55856777050   |
      | 19626829001   |
      | 24094592008   |
      | 58063164083   |

  @cpf-sem-restricao  @ok
  Cenario: Validar uma Consulta a um sem CPF com restricao
    Dado que possuo acesso a API de restricoes
    Quando envio um resquest de um CPF "40508095832" com dados validos
    Então o status code deve ser 204

  @consulta-lista-simulacao  @ok
  Cenario: Validar a Consulta de todas Simulacoes para API de Simulacao
    Dado que possuo acesso a API de Simulacoes
    Quando envio um resquest para o recurso correto
    Então devo receber uma lista de simulacoes cadastradas
    E o status code deve ser 200

  @consulta-simulacao-cpf @melhorar
  Cenario: Validar a Consulta deum Simulacoes para API de Simulacao por CPF
    Dado que possuo acesso a API de Simulacoes
    Quando envio um resquest para API simulacoes com CPF "78040447046"
    Então retorna a simulação cadastrada
    E o status code deve ser 200

  @criar-simulacao  @ok
  Cenario: Validar a criacao de uma simulacao de credito
    Dado que possuo acesso a API de Simulacoes para criar uma simulacao
    Quando envio um resquest para criacao de uma simulacao
    Então retorna a simulação com os dados cadastrados
    E o status code deve ser 201

  @criar-simulacao-error  @ok
  Cenario: Validar a resposta de mensagem de erro
    Dado que possuo acesso a API de Simulacoes para criar uma simulacao
    Quando envio um resquest para criacao de uma simulacao sem o CPF
    Então retorna a simulação com os a mensagem de erro
    E o status code deve ser 400

  @simulacao-cpf-existente
  Cenario: Validar a criacao de uma simulacao com CPF duplicado
    Dado que possuo acesso a API de Simulacoes para criar uma simulacao
    Quando envio um resquest para criacao de uma simulacao com CPF "59714696062"
    Então retorna a simulação com os a mensagem de erro "CPF duplicado"
    E o status code deve ser 400

  @alterar-simulacao  @ok
  Cenario: Validar a alteracao de uma simulacao existente
    Dado que possuo acesso a API de Simulacoes para alterar uma simulacao existente
    Quando envio um resquest para alteracao de uma simulacao passando CPF "66414919004" existente
    Então retorna a simulação com os dados alterados
    E o status code deve ser 200

  @deletar-simulacao  @ok
  Cenario: Validar a exclusao de uma simulacao existente
    Dado que possuo acesso a API de Simulacoes para deletar uma simulacao existente
    Quando envio um resquest para deletar uma simulacao passando CPF "13091400577" existente
    Então o status code deve ser 200



