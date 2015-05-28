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
    self.button = $('#go', container);
    self.button.addEventListener('click', function () {
        $.ajax('/primeFactors?number=' + self.input.value, function (data) {
            self.result.innerHTML = self.render(data);
        });
    });
}
PrimeFactors.prototype.render = function (result) {
    if (result.error) {
        return result.error;
    }
    return result.number + ' = ' + result.decomposition.join(' x ');
};