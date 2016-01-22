'use strict';

App.factory('UsuarioService', ['$http', '$q', function($http, $q){

	return {

		listAll: function() {
			return $http.get('http://localhost:8080/springangular/usuario/')
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Erro ao listar os usuários.');
						return $q.reject(errResponse);
					}
			);
		},

		create: function(usuario){
			return $http.post('http://localhost:8080/springangular/usuario/', usuario)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Erro ao criar usuário.');
						return $q.reject(errResponse);
					}
			);
		},

		update: function(usuario, id){
			return $http.put('http://localhost:8080/springangular/usuario/' + id, usuario)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Erro ao atualizar usuário.');
						return $q.reject(errResponse);
					}
			);
		},

		remove: function(id){
			return $http.delete('http://localhost:8080/springangular/usuario/' + id)
			.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Erro ao excluir usuário.');
						return $q.reject(errResponse);
					}
			);
		}

	};

}]);
