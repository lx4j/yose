describe('primefactors', function () {
    var primeFactors;
    beforeEach(function () {
        var console = $('#console');
        console.innerHTML = "<input id='number'/><button id='go'>Decompose</button><div id='result'></div><ol id='results'></ol>";
        primeFactors = new PrimeFactors('#console');
    });
    describe("query", function () {

        it("empty", function () {
            expect(primeFactors.query).to.equal('')
        });

        it("single numbers", function () {
            $('#number').value = '8';
            expect(primeFactors.query).to.equal('number=8')
        });

        it("multi numbers", function () {
            $('#number').value = '8,3,hello';
            expect(primeFactors.query).to.equal('number=8&number=3&number=hello')
        });

        it("trimming numbers", function () {
            $('#number').value = ' 8 , 3 , hello ';
            expect(primeFactors.query).to.equal('number=8&number=3&number=hello')
        });
    });
    describe("render", function () {
        it("decomposition", function () {
            expect(primeFactors.render({number: 6, decomposition: [2, 3]})).to.equal('6 = 2 x 3');
        });

        it("too big number", function () {
            expect(primeFactors.render({
                number: 100000000,
                error: 'too big number (\u003e1e6)'
            })).to.equal('too big number (>1e6)');
        });

        it("NaN", function () {
            expect(primeFactors.render({number: '3hello', error: 'not a number'})).to.equal('3hello is not a number');
        });

        it("number < 1", function () {
            expect(primeFactors.render({
                number: '-1',
                error: 'not an integer > 1'
            })).to.equal('-1 is not an integer > 1');
        });
    });

    describe("decompose", function () {
        it("single", function () {
            $.ajax = function (url, callback) {
                expect(url).to.equal('/primeFactors?number=8');
                return callback({number: 8, decomposition: [2, 2, 2]});
            };

            $('#results').innerHTML = 'history';
            $('#number').value = 8;
            $('#go').click();

            expect($('#results').innerHTML).to.equal('');
            expect($('#result').innerHTML).to.equal('8 = 2 x 2 x 2');
        });


        it("multi entries", function () {
            $.ajax = function (url, callback) {
                expect(url).to.equal('/primeFactors?number=8&number=hello');
                return callback([
                    {number: 8, decomposition: [2, 2, 2]},
                    {number: 'hello', error: 'not a number'}
                ]);
            };
            $('#result').innerHTML = 'history';
            $('#number').value = '8,hello';
            $('#go').click();

            expect($('#result').innerHTML).to.equal('');
            expect($('#results').innerHTML).to.match(/hello is not a number/);
            expect($('#results').innerHTML).to.match(/8 = 2 x 2 x 2/);
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