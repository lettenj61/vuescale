var webpack = require('webpack');

module.exports = require('./scalajs.webpack.config.js');

module.exports.resolve = {
  alias: {
    'vue$': 'vue/dist/vue.common.js'
  }
}