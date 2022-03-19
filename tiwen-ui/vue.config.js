let proxyObj = {}

proxyObj['/'] = {

    ws: false,

    target: 'http://127.0.0.1:8082',

    changeOrigin: true,

    pathReWrite: {
        '^/': '/'
    }
}

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        Proxy: proxyObj
    }
}