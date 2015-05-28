var gulp = require('gulp');
var mochaPhantomJS = require('gulp-mocha-phantomjs');

gulp.task('default', function () {
    gulp.src('src/test/js/*.html').pipe(mochaPhantomJS());
});