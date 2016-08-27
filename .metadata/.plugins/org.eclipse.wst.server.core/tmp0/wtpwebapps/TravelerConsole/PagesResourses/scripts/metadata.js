define(function(){
    return {
        pageGroups: [{"id":"44a4ebcb-f90b-ff52-dd5e-26eef74b1f27","name":"Default group","pages":[{"id":"a11b95db-6b94-81b9-75bc-2ad5ac264c31","name":"SamplePage"},{"id":"29d12b1c-26f6-2fc6-a36f-5aabfbf3f5fe","name":"SamplePageSmaller"},{"id":"LoginPage","name":"LoginPage"},{"id":"RegistrationPage","name":"RegistrationPage"},{"id":"8ece9481-8952-955c-ca3f-3cdaa99a1f38","name":"Homepage"},{"id":"f10f91fe-00d2-5c0d-a276-9894571ba6af","name":"ProfilePreview"}]}],
        downloadLink: "//services.ninjamock.com/html/htmlExport/download?shareCode=R28VD&projectName=Untitled project",
        startupPageId: 0,

        forEachPage: function(func, thisArg){
        	for (var i = 0, l = this.pageGroups.length; i < l; ++i){
                var group = this.pageGroups[i];
                for (var j = 0, k = group.pages.length; j < k; ++j){
                    var page = group.pages[j];
                    if (func.call(thisArg, page) === false){
                    	return;
                    }
                }
            }
        },
        findPageById: function(pageId){
        	var result;
        	this.forEachPage(function(page){
        		if (page.id === pageId){
        			result = page;
        			return false;
        		}
        	});
        	return result;
        }
    }
});
