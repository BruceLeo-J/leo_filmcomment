
        Do.ready(
            'https://img3.doubanio.com/f/movie/a93125b2c78a460d3771db7d2ab75a3b10fa103f/js/movie/slide.js',
            'https://img3.doubanio.com/f/movie/ecbdaf591e20910d54ee26e44fcb37dea31b509d/js/movie/subject_detail_tip.js',
            function(){
                var screeningSlide = new Slide({
                    autoplay: true,
                    wrap: $('#screening .screening-bd'),
                    speed: 600,
                    duration: 20000,
                    itemsPerSlide: 5,
                    lazyload: false
                });
                var galleryID="#hot-gallery",gallerySlide=new Slide({autoplay:!0,wrap:$(galleryID),speed:1e3,duration:5e3,itemsPerSlide:1,index:".gallery-ui-slide-index",max:".gallery-ui-slide-max",btnPrev:".gallery-btn-prev",btnNext:".gallery-btn-next",controlNav:".gallery-ui-control-nav",lazyload:!1});$(galleryID).find("a").click(function(){var l=$(this).data("fid");$.post("/j/misc/gallery/click_count",{frame_id:l,ck:get_cookie("ck")})});;
                $('#screening .ui-slide-item img').subjectTip('.ui-slide-item', 'screening');
            });
    ;
      $('.hot_link').find('a').click(function(){
          var buzz_id = $(this).data("bid");
          $.post_withck('/j/misc/buzz/click_count', { buzz_id: buzz_id })
      });
  ;
        var gaiaConfig = [{
            type: 'movie',
            source: 'index',
            selector: '.gaia-movie',
            hashbang: false,
            fixFilter: false,
            slide: {
              pageCount: 5,
              pageLimit: 10,
              slideWidth: 700,
              slideHeight: 426,
            },
            is_mobile: "False"
        }, {
            type: 'tv',
            source: 'index',
            selector: '.gaia-tv',
            hashbang: false,
            fixFilter: false,
            slide: {
              pageCount: 5,
              pageLimit: 10,
              slideWidth: 700,
              slideHeight: 426,
            },
            is_mobile: "False"
        }];

        Do.add('_', {path: 'https://img3.doubanio.com/f/movie/6a8dbf6555c94010e1299fb569121d5804a5ea0f/js/movie/lib/underscore.js', type: 'js'});
        Do.add('lazyload', {path: 'https://img3.doubanio.com/f/shire/5688df2ab9b7ba25e651e0d1b87daeaf8c54dd93/js/jquery.lazyload.min.js', type: 'js'});
        Do.add('deparam', {path: 'https://img3.doubanio.com/f/movie/01a2b255ada7c2ffda93e804ddc82de25ad5c76e/js/movie/lib/jquery.deparam.js', type: 'js'});
        Do.add('gaia', {path: 'https://img3.doubanio.com/f/movie/5ce873715d72e8239890b06c69b8c497fdaec57a/js/movie/project/gaia/__init__.js', type: 'js'});

        Do.ready('_', 'lazyload', 'deparam', 'gaia', function(){

            $('.lazy').lazyload({threshold: 350, effect: 'fadeIn'});

            $('#city-id').bind('click', function(e){
                $('#cinemas-suggestion-input input').blur();
                return false;
            });

            $(document)
                .delegate('.poster img', 'mouseenter', function(e){
                    $('#cities-list').hide();
                    $('#cinemas-suggestion-input input').blur();
                });

            $('#cinemas-suggestion-input input').val('').blur();
        });
    