function $(id, context) {
    context = context || document;
    return context.querySelector(id);
}
$.ajax = function (url, done) {
    var request = new XMLHttpRequest();
    request.open('GET', url);
    request.onload = function () {
        done($.encode(request.responseText))
    };
    request.send();
};
$.encode = function (data) {
    return new Function('return ' + data).call();
};
function PrimeFactors(container) {
    container = $(container);
    var self = this;
    self.input = $('#number', container);
    self.result = $('#result', container);
    self.results = $('#results', container);
    self.button = $('#go', container);
    Object.defineProperty(self, 'query', {
        get: function () {
            var numbers = self.input.value;
            return numbers ? numbers.split(',').map(function (item) {
                return 'number=' + item.replace(/^\s+|\s+$/g, '');
            }).join('&') : '';
        }
    });
    self.button.addEventListener('click', function () {
        $.ajax('/primeFactors?' + self.query, function (data) {
            self.results.innerHTML = '';
            self.result.innerHTML = '';

            if (data instanceof Array) {
                self.results.innerHTML = data.map(function (item) {
                    return '<li>' + self.render(item) + '</li>';
                }).join('');
            } else {
                self.result.innerHTML = self.render(data);
            }
        });
    });
}
PrimeFactors.prototype.render = function (result) {
    if (result.error) {
        return isNaN(result.number) || parseInt(result.number) < 1 ? [result.number, result.error].join(' is ') : result.error;
    }
    return result.number + ' = ' + result.decomposition.join(' x ');
};