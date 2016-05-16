flyk.directive("uiCep", function ($filter) {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) { // element, atributos do element,
            var _formatCEP = function (cep) {
                cep = cep.replace(/[^0-9]+/g, ""); // Substitui tudo que não for de 0-9 por "".
                if (cep.length > 5) {
                    cep = cep.substring(0, 5) + "-" + cep.substring(5, 8); // Se a data for maior que 2, determina que as duas primeiras substrings(0 e 1) + / + todo o resto da string.
                }
                return cep;
            };

            element.bind("keyup", function () { //keyup assim que começa a digitar os comentários, muda os valores.
                ctrl.$setViewValue(_formatCEP(ctrl.$viewValue)); //
                ctrl.$render(); //renderiza depois de setar o value
            });



            ctrl.$formatters.push(function (value) {
                return $filter("cep")(value, "xxx.xxx.xxx-xx");
            });
        }
    };
});
