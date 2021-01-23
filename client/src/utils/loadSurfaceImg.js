class LoadSurfaceImg {
  
    getImgUrl(surface) {
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
            console.log(`Sorry, we are out of ${surface}.`);
        }
      }

}

export default new LoadSurfaceImg();
