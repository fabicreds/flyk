flyk.directive("uiTelefone", function ($filter) {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) { // element, atributos do element,
            var _formatTelefone = function (telefone) {
                telefone = telefone.replace(/[^0-9x]+/g, ""); // Substitui tudo que não for de 0-9 por "".
               // telefone.substring(0) ="("
                if (telefone.length > 2) {
                    telefone = telefone.substring(0, 2) + ")" + telefone.substring(2); // Se a data for maior que 2, determina que as duas primeiras substrings(0 e 1) + / + todo o resto da string.
                }
                if (telefone.length > 8) {
                    telefone = telefone.substring(0, 7) + "-" + telefone.substring(7,11); // Se a data for maior que 5, determina que as duas primeiras substrings(0,1,2,3,4) + / + todo o resto da string.
                }
              
                return "(" + telefone;
            };

            element.bind("keyup", function () { //keyup assim que começa a digitar os comentários, muda os valores.
                ctrl.$setViewValue(_formatTelefone(ctrl.$viewValue)); //
                ctrl.$render(); //renderiza depois de setar o value
            });



            ctrl.$formatters.push(function (value) {
                return $filter("telefone")(value, "(xx).xxxx-xxxx");
            });
        }
    };
});
