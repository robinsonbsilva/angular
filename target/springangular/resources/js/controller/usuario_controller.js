'use strict';

App.controller('UsuarioController', ['$scope', 'UsuarioService', function($scope, UsuarioService) {

	var self = this;
	self.usuario={id:null, nome:'', endereco:'', email:''};
	self.usuarios=[];

	self.listAll = function(){
		UsuarioService.listAll()
		.then(
				function(d) {
					self.usuarios = d;
				},
				function(errResponse){
					console.error('Erro ao listar os usuários.');
				}
		);
	};

	self.create = function(usuario){
		UsuarioService.create(usuario)
		.then(
				self.listAll, 
				function(errResponse){
					console.error('Erro ao criar usuário.');
				}	
		);
	};

	self.update = function(usuario, id){
		UsuarioService.update(usuario, id)
		.then(
				self.listAll, 
				function(errResponse){
					console.error('Erro ao atualizar usuário.');
				}	
		);
	};

	self.remove = function(id){

		for(var i = 0; i < self.usuarios.length; i++){
			if(self.usuarios[i].id == id) {
				self.reset();
				break;
			}
		}

		UsuarioService.remove(id)
		.then(
				self.listAll, 
				function(errResponse){
					console.error('Erro ao excluir usuário.');
				}	
		);
	};

	self.listAll();

	self.submit = function() {
		if(self.usuario.id == null){
			self.create(self.usuario);
		}else{
			self.update(self.usuario, self.usuario.id);
		}
		self.reset();
	};

	self.edit = function(id){
		for(var i = 0; i < self.usuarios.length; i++){
			if(self.usuarios[i].id == id) {
				self.usuario = angular.copy(self.usuarios[i]);
				break;
			}
		}
	};

	self.reset = function(){
		self.usuario={id:null, nome:'', endereco:'', email:''};
		$scope.myForm.$setPristine(); //reset Form
	};

}]);
