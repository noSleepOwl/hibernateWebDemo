//所有第三方js加载完成后 ， app_tools.js加载完成后执行
let base = {};
/**
 * 初始化菜单
 */
base.initMuseum = function () {
    appMuseum.createMuseum();
};

$(() => {
    base.initMuseum();
});