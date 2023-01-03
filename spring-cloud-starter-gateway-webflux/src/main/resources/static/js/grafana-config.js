const resizeIframe = function(idIframe, heightToRemove){
    let iframe = $(idIframe);
    let height = $(window).height() - heightToRemove - 6;
    iframe.css('height', `${height}px`);

    $(window).on('resize', function(){
        let iframe = $(idIframe);
        let height = $(window).height() - heightToRemove - 6;
        iframe.css('height', `${height}px`);
    });
}