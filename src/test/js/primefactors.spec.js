describe('primefactors', function () {
    var primeFactors;
    beforeEach(function () {
        var console = $('#console');
        console.innerHTML = "<input id='number'/><button id='go'>Decompose</button><div id='result'></div>";
        primeFactors = new PrimeFactors('#console');
    });

    describe("render", function () {
        it("decomposition", function () {
            expect(primeFactors.render({number: 6, decomposition: [2, 3]})).to.equal('6 = 2 x 3');
        });

        it("too big number", function () {
            expect(primeFactors.render({number: 100000000, error: 'too big number (\u003e1e6)'})).to.equal('too big number (>1e6)');
        });

        it("NaN", function () {
            expect(primeFactors.render({number: '3hello', error: 'not a number'})).to.equal('3hello is not a number');
        });
    });

    describe("decompose", function () {
        it("decompose", function () {
            $.ajax = function (url, callback) {
                expect(url).to.equal('/primeFactors?number=8');
                return callback({number: 8, decomposition: [2, 2, 2]});
            };

            $('#number').value = 8;
            $('#go').click();

            expect($('#result').innerHTML).to.equal('8 = 2 x 2 x 2');
        });
    });

});
describe("ajax", function () {
    it("encode", function () {
        var data = '{"error":"too big number (\u003e1e6)","number":"100000000"}';
        var result = $.encode(data);
        expect(result.number).to.equal('100000000');
        expect(result.error).to.equal('too big number (>1e6)');
    });
});