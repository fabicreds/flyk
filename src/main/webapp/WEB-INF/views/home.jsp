 <body>
       
        <div ng-controller="homeCtrl" id="slides_control">
        <div>
          <uib-carousel interval="myInterval">
            <uib-slide ng-repeat="slide in slides" active="active" index="$index">
              <img ng-src="{{slide.image}}" style="margin:auto;" />
              <div class="carousel-caption">
                <h4>Slide {{$index+1}}</h4>
              </div>
            </uib-slide>
          </uib-carousel>
          </div>
          </div>
  </body>
