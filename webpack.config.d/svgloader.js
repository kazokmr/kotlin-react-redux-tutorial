config.module.rules.push({
  test: /\.(png|svg|jpg|jpeg|gif|tiff|bmp)$/,
  use: [{
    loader: 'file-loader',
    options: {
      name: '[name].[ext]'
    }
  }]
});