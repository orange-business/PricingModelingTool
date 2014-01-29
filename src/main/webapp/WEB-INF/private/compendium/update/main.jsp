<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap styles -->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<!-- Generic page styles -->
<link rel="stylesheet" href="/js/fileupload/css/style.css">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="/js/fileupload/css/jquery.fileupload.css">
<div class="container">
  <blockquote><p>File Upload widget with multiple file selection, drag&amp;drop support and progress bar for
    jQuery.<br/>
    Supports cross-domain, chunked and resumable file uploads.</p></blockquote>
  <!-- The fileinput-button span is used to style the file input field as button -->
  <span class="btn btn-success fileinput-button">
    <i class="glyphicon glyphicon-plus"></i>
    <span>Select files...</span>
    <!-- The file input field used as target for the file upload widget -->
    <input id="fileupload" type="file" name="files[]" multiple>
  </span>
  <br/>
  <!-- The global progress bar -->
  <div id="progress" class="progress">
    <div class="progress-bar progress-bar-success"></div>
  </div>
  <!-- The container for the uploaded files -->
  <div id="files" class="files"></div>
  <div id="error" class="error"></div>
  <br>
  <div class="panel panel-default">
    <div class="panel-heading"><h3 class="panel-title">Замечания</h3></div>
    <div class="panel-body">
      <ul>
        <li>The maximum file size for uploads in this demo is <strong>5 MB</strong> (default file size is unlimited).</li>
        <li>Only image files (<strong>JPG, GIF, PNG</strong>) are allowed in this demo (by default there is no file type restriction).</li>
        <li>You can <strong>drag &amp; drop</strong> files from your desktop on this webpage (see <a
            href="https://github.com/blueimp/jQuery-File-Upload/wiki/Browser-support">Browser support</a>).
        </li>
      </ul>
    </div>
  </div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="/js/fileupload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="/js/fileupload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="/js/fileupload/js/jquery.fileupload.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script>
  /*global window, $ */
  $(function () {
    'use strict';
    $('#fileupload').fileupload({
      url:'/compendiumFiles/upload',
      dataType:'json',
      done:function (e, data) {
//        // we have the response
//        if (e.status == "SUCCESS") {
//          var userInfo = "<ol>";
//          for (var i = 0; i < e.result.length; i++) {
//            userInfo += "<br><li><b>Name</b> : " + e.result[i].name + ";<b> Education</b> : " + e.result[i].education;
//          }
//          userInfo += "</ol>";
//          $('#info').html("User has been added to the list successfully. " + userInfo);
//          $('#name').val('');
//          $('#education').val('');
//          $('#error').hide('slow');
//          $('#info').show('slow');
//        } else {
//          var errorInfo = "";
//          for (i = 0; i < e.result.length; i++) {
//            errorInfo += "<br>" + (i + 1) + ". " + e.result[i].code;
//          }
//          $('#error').html("Please correct following errors: " + errorInfo);
//          $('#info').hide('slow');
//          $('#error').show('slow');
//        }
        alert("hello data " + data.result.files);

        $.each(data.result.files, function(index, file) {
          alert("file.name " + file.name);
          $('<p/>').text(file.name).appendTo('#files');
        });
      },
      progressall:function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css('width', progress + '%');
      },
      error:function (e) {
        alert('Error: ' + e);
      }
    }).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
  });
</script>