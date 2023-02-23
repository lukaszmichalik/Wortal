class LoadSurfaceImg {

  getImg(surface) {
    var images = require.context('../assets/', false);
    switch (surface) {
      case 'hala':
        return images('./' + surface + '.jpg');
      case 'naturalna':
        return images('./' + surface + '.jpg');
      case 'sztuczna':
        return images('./' + surface + '.jpg');
      case 'tartan':
        return images('./' + surface + '.jpg');
      default:
        break;
    }
  }

}

export default new LoadSurfaceImg();
