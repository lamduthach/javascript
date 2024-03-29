var downloadFileByAjax = function () {
    $.ajax({
        url: "downloadFile",
        type: "POST",
        success: function (response) {
            try {
                var responseJson = $.parseJSON(response);
                if (responseJson.hasOwnProperty("errorType")) {
                    showNotifications(responseJson.message, "error");
                } else {
                    showNotifications('Getting File error. Sorry!', 'error');
                }
            } catch (e) {
                // This is base64, so it can not parese to Json
                saveToFile('filename', response);
            }
        },
        error: function (e) {
            showNotifications('Send data for download error. Sorry!', 'error');
        }
    });
};

var saveToFile = function (name, dataBase64) {
    if (navigator.msSaveOrOpenBlob) {
        navigator.msSaveOrOpenBlob(uriToBlob(dataBase64), name);
        return;
    }
    var saveLink = document.createElement('a');
    var downloadSupported = 'download' in saveLink;
    if (!downloadSupported) {
        window.open(dataBase64, '_temp', 'menubar=no,toolbar=no,status=no');
        return;
    }

    saveLink.download = name;
    saveLink.style.display = 'none';
    document.body.appendChild(saveLink);
    try {
        var blob = base64ToBlob(dataBase64);
        var url = URL.createObjectURL(blob);
        saveLink.href = url;
        saveLink.onclick = function () {
            requestAnimationFrame(function () {
                URL.revokeObjectURL(url);
            });
        };
    } catch (e) {
        saveLink.href = dataBase64;
    }
    saveLink.click();
    document.body.removeChild(saveLink);
};

var base64ToBlob = function (dataBase64) {
    var byteString = window.atob(dataBase64);
    var buffer = new ArrayBuffer(byteString.length);
    var intArray = new Uint8Array(buffer);
    for (var i = 0; i < byteString.length; i++) {
        intArray[i] = byteString.charCodeAt(i);
    }
    return new Blob([buffer], {type: "application/octet-stream"});
};
