/**
 * INSPINIA - Responsive Admin Theme
 *
 */

/**
 * MainCtrl - controller
 */
function MainCtrl() {

    this.userName = 'Example user';
    this.helloText = 'Welcome in SeedProject';
    this.descriptionText = 'It is an application skeleton for a typical AngularJS web app. You can use it to quickly bootstrap your angular webapp projects and dev environment for these projects.';

};

function LoginCtrl($scope) {

    this.userName2 = 'Example user2';
    
    
    /* message on eventClick */
    $scope.login = function(  ){
    	alert("dsdd");
        $scope.alertMessage = (event.title + ': Clicked ');
    };
    

};


angular
    .module('inspinia')
    .controller('MainCtrl', MainCtrl)
    .controller('LoginCtrl', LoginCtrl)