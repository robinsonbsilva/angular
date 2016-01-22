<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>Gerenciamento de Usuários</title>  
    <style>
      .nome.ng-valid {
          background-color: lightgreen;
      }
      .nome.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .nome.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }

      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }

    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="UsuarioController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Cadastro de Usuário</span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.usuario.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Nome</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.usuario.nome" name="txtNome" class="nome form-control input-sm" placeholder="Informe o nome" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.txtNome.$error.required">Esse campo é requerido</span>
                                      <span ng-show="myForm.txtNome.$error.minlength">Tamanho minimo requerido é 3 caracteres</span>
                                      <span ng-show="myForm.txtNome.$invalid">Campo com valor inválido</span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">Endereço</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.usuario.endereco" class="form-control input-sm" placeholder="Informe o endereço"/>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="file">E-mail</label>
                              <div class="col-md-7">
                                  <input type="email" ng-model="ctrl.usuario.email" name="txtEmail" class="email form-control input-sm" placeholder="Informe o e-mail" required/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.txtEmail.$error.required">Esse campo é requerido</span>
                                      <span ng-show="myForm.txtEmail.$invalid">Campo com valor inválido</span>
                                  </div>
                              </div>
                          </div>
                      </div>

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.usuario.id ? 'Salvar' : 'Atualizar'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Limpar</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">Lista de Usuários</span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Código</th>
                              <th>Nome</th>
                              <th>Endereço</th>
                              <th>E-mail</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="usuario in ctrl.usuarios">
                              <td><span ng-bind="usuario.id"></span></td>
                              <td><span ng-bind="usuario.nome"></span></td>
                              <td><span ng-bind="usuario.endereco"></span></td>
                              <td><span ng-bind="usuario.email"></span></td>
                              <td>
								<button type="button" ng-click="ctrl.edit(usuario.id)" class="btn btn-success custom-width">Alterar</button> 
                              	<button type="button" ng-click="ctrl.remove(usuario.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
      
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/resources/js/app.js' />"></script>
      <script src="<c:url value='/resources/js/service/usuario_service.js' />"></script>
      <script src="<c:url value='/resources/js/controller/usuario_controller.js' />"></script>
  </body>
</html>