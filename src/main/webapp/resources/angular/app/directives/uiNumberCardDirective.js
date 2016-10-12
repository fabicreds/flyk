flyk.directive("uiNumberCard", function ($filter) {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) { // element, atributos do element,
            var _formatnumbercard = function (numbercard) {
               numbercard = numbercard.replace(/[^0-9x]+/g, ""); // Substitui tudo que não for de 0-9 por "".
                if (numbercard.length > 4) {
                    numbercard = numbercard.substring(0, 4) + "." + numbercard.substring(4); // Se a data for maior que , determina que as duas primeiras substrings(0 e 1) + / + todo o resto da string.
                }
                if (numbercard.length > 9) {
                    numbercard = numbercard.substring(0, 9) + "." + numbercard.substring(9); // Se a data for maior que 5, determina que as duas primeiras substrings(0,1,2,3,4) + / + todo o resto da string.
                }
                if (numbercard.length > 14) {
                    numbercard = numbercard.substring(0, 14) + "." + numbercard.substring(14,18); // Se a data for maior que 5, determina que as duas primeiras substrings(0,1,2,3,4) + / + todo o resto da string.
                }

                return numbercard;
            };

            element.bind("keyup", function () { //keyup assim que começa a digitar os comentários, muda os valores.
                ctrl.$setViewValue(_formatnumbercard(ctrl.$viewValue)); //
                ctrl.$render(); //renderiza depois de setar o value
            });



            ctrl.$formatters.push(function (value) {
                return $filter("numbercard")(value, "xxx.xxx.xxx-xx");
            });
        }
    };
});
