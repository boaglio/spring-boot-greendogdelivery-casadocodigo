var app = angular.module("delivery",["checklist-model"],function($locationProvider){
    $locationProvider.html5Mode({
    	  enabled: true,
    	  requireBase: false
   	});
});


app.controller('pedidoController', function($scope,$location,$http) {

	$scope.itens = [];
	$scope.subTotal = 0;
	$scope.pedidoItens=[];
	
	 var carregaOferta= function () {
	        $http.get( "/oferta").success(function (data) {
	          $scope.oferta = data["mensagem"];
	          $scope.servidor = data["servidor"];
	          $scope.debug = data["debug"];
	        }).error(function (data, status) {
	          $scope.message = "Aconteceu um problema: " + data;
	        });
	      };
	      
	 var carregarItens= function () {
	        $http.get( "/api/itens").success(function (data) {
	          $scope.itens =  data["_embedded"]["itens"];
	        }).error(function (data, status) {
	          $scope.message = "Aconteceu um problema: " + data;
	        });
	      };
	      
      $scope.fazerPedido = function(pedidoItens) {
    	  $scope.message ="";
    	  var pedidoStr="";
    	  var prefixo="";
    	  for (var i=0; i< $scope.pedidoItens.length; i++) {
   		   pedidoStr+=prefixo+$scope.pedidoItens[i].id;
   		   prefixo=",";
          }
    	  $scope.urlPedido="/rest/pedido/novo/2/"+pedidoStr;
	      $http.get( $scope.urlPedido).success(function (data) {
	        $scope.idPedido= data["pedido"];
	        $scope.mensagem= data["mensagem"];
	        $scope.valorTotal= data["valorTotal"];
	      }).error(function (data, status) {
	        $scope.message = "Aconteceu um problema: " 
	        +"Status:"+ data.status+ " - error:"+data.error;
	      });
	    };

     $scope.isItemSelecionado = function() { 
    	 
    	 if (this.checked)
   		  $scope.subTotal+=this.i.preco;
    	 else
   		  $scope.subTotal-=this.i.preco;    		 

     }

   carregarItens();
   carregaOferta();
	  
});
 