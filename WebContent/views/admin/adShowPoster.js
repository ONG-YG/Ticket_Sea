uploadFile = function() {
  var file = document.getElementById('show_poster');
  var filedata = new FormData(); // FormData 인스턴스 생성

  if (!file.value) return; // 파일이 없는 경우 빠져나오기

  filedata.append('uploadfile', file.files[0]);

  var _xml = new XMLHttpRequest();
  _xml.open('POST', '/img/poster/', true);
  _xml.onload = function(event) {
    if (_xml.status == 200) {
      alert('Uploaded');
    }
    else {
      alert('Error');
    }
  };

  _xml.send(filedata);
};