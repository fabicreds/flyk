flyk.directive("uiCpf", function ($filter) {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) { // element, atributos do element,
            var _formatCPF = function (cpf) {
                cpf = cpf.replace(/[^0-9x]+/g, ""); // Substitui tudo que não for de 0-9 por "".
                if (cpf.length > 3) {
                    cpf = cpf.substring(0, 3) + "." + cpf.substring(3); // Se a data for maior que 2, determina que as duas primeiras substrings(0 e 1) + / + todo o resto da string.
                }
                if (cpf.length > 7) {
                    cpf = cpf.substring(0, 7) + "." + cpf.substring(7); // Se a data for maior que 5, determina que as duas primeiras substrings(0,1,2,3,4) + / + todo o resto da string.
                }
                if (cpf.length > 11) {
                    cpf = cpf.substring(0, 11) + "-" + cpf.substring(11, 13); // Se a data for maior que 5, determina que as duas primeiras substrings(0,1,2,3,4) + / + todo o resto da string.
                }
                return cpf;
            };

            element.bind("keyup", function () { //keyup assim que começa a digitar os comentários, muda os valores.
                ctrl.$setViewValue(_formatCPF(ctrl.$viewValue)); //
                ctrl.$render(); //renderiza depois de setar o value
            });



            ctrl.$formatters.push(function (value) {
                return $filter("cpf")(value, "xxx.xxx.xxx-xx");
            });
        }
    };
});
