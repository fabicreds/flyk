flyk.directive("uiDate", function ($filter) {
    return {
        require: "ngModel",
        link: function (scope, element, attrs, ctrl) { // element, atributos do element,
            var _formatDate = function (date) {
                date = date.replace(/[^0-9]+/g, ""); // Substitui tudo que não for de 0-9 por "".
                if (date.length > 2) {
                    date = date.substring(0, 2) + "/" + date.substring(2); // Se a data for maior que 2, determina que as duas primeiras substrings(0 e 1) + / + todo o resto da string.
                }
                if (date.length > 5) {
                    date = date.substring(0, 5) + "/" + date.substring(5, 9); // Se a data for maior que 5, determina que as duas primeiras substrings(0,1,2,3,4) + / + todo o resto da string.
                }
                return date;
            };

            element.bind("keyup", function () {
                ctrl.$setViewValue(_formatDate(ctrl.$viewValue)); //
                ctrl.$render(); //renderiza depois de setar o value
            });



            ctrl.$formatters.push(function (value) {
                return $filter("date")(value, "dd/MM/yyyy");
            });
        }
    };
});