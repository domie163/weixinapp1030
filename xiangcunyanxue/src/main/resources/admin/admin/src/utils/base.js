const base = {
    get() {
        return {
            url : "http://localhost:8080/xiangcunyanxue/",
            name: "xiangcunyanxue",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/xiangcunyanxue/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "乡村研学旅行平台"
        } 
    }
}
export default base
