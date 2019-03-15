var params={
        // pageSize:10,
        // pageNo:1

        content: null,//content,
        offset: 0,//offset,
        limit: 10,//limit,
        order: null//order
    };

var rowSize=1;  
var curPage=1;


// var app = angular.module('rent', []);
// app.controller('rentCtrl', function($scope,$http) {
  
// })


function search($http,$scope){
            // params.pageNo=pageNo;
           /* $http({
                method: 'POST',
                url:'/itemspage',
                params:data
            })*/
            var off=(curPage-1)*10;
            var data =JSON.stringify({
                                content: null,//content,
                                offset:off,//offset,0  9  10 19
                                limit: 10,//limit,
                                order: null//order
                        });   

            $http.post("/itemspage",data)
            .then(function (res) {
                $scope.items = res.data.data;
                // 数据总条数
                $scope.total = res.data.total;
                // 数据总页数
                $scope.rowSize = res.data.rowSize;
                rowSize=$scope.rowSize;
                // 数据当前页
                $scope.cur = curPage;
            }, function (err) {
                // 请求失败执行代码
                alert(err.data)
            });
        }
function init($scope,$http){
        search($http,$scope);
        $scope.nextPage=function(){
            nextPage($http,$scope);
        };
        $scope.lastPage=function(){
            lastPage($http,$scope);
        };
    }
// 点击上一页
function lastPage($http,$scope){
    if(curPage>1){
        curPage--;
        search($http,$scope);
    }
}

function nextPage($http,$scope){
    if(curPage<rowSize){
        curPage++;
        // ++curPage;
        search($http,$scope);
        if(curPage>rowSize){
            alert("已经是最后一页");
            return;
        }
    }
 }
