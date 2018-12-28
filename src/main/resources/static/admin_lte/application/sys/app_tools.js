// 工具方法 第三方js加载完成之后 执行
let tools = {};
// 所有路径
tools.paths = {};
// 项目基础路径
tools.paths.base = webctx;
// 系统控制器
tools.paths.sys = {};
tools.paths.sys.controller = [tools.paths.base, "sys"].join("");
tools.paths.sys.museum = [tools.paths.sys.controller, "getMuseums"].join("/");

tools.isFunc = function (target) {
    return target && typeof target === 'function';
};
