<style>
/********************************/
/*          Hero Headers        */
/********************************/
.hero {
    position: absolute;
    top: 50%;
    left: 50%;
    z-index: 3;
    color: #fff;
    text-align: center;
    text-transform: uppercase;
    text-shadow: 1px 1px 0 rgba(0,0,0,.75);
      -webkit-transform: translate3d(-50%,-50%,0);
         -moz-transform: translate3d(-50%,-50%,0);
          -ms-transform: translate3d(-50%,-50%,0);
           -o-transform: translate3d(-50%,-50%,0);
              transform: translate3d(-50%,-50%,0);
}
.hero h1 {
    font-size: 6em;    
    font-weight: bold;
    margin: 0;
    padding: 0;
}
</style> 
 
 <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
 <body>
       
        <div ng-controller="homeCtrl" id="slides_control" style="margin-top:-20px">
        <div>
          <uib-carousel interval="myInterval">
            <uib-slide ng-repeat="slide in slides" active="active" index="$index">
              <img ng-src="{{slide.image}}" style="margin:auto;  height:92%; width:100%" />
              <div class="carousel-caption">
                <hgroup>
            		<h1>{{slide.text}}</h1>        
            		<h3>{{slide.subtext}}</h3>
        		</hgroup>
        		</div>
            </uib-slide>
          </uib-carousel>
          </div>
          </div>
  </body>
