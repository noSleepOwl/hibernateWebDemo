function clearConsole() {
    $("#content").empty();
}

$(function () {
    const label_sql = '<span class="label label-primary">sql</span>';
    const label_param = '<span class="label label-info">param</span>';
    let page_index = 0;
    var stompClient = null;
    examplePages = examplePages.map(o => webctx + o);
    let currentPage = "";
    connect();
    nextPage();

    function getLabel_sql(date) {
        return `<span class="label label-primary">sql${date}</span>`;
    }

    // prettyPrint();

    function connect() {
        if (stompClient == null) {
            var socket = new SockJS(webctx + 'websocket?token=kl');
            stompClient = Stomp.over(socket);
            stompClient.connect({token: "kl"}, function (frame) {
                stompClient.subscribe('/topic/pullLogger', function (greeting) {
                    var body = JSON.parse(greeting.body);
                    let msg = createConnsoleInfo(body);
                    $("#content").append(msg);
                    hljs.configure({useBR: true});
                    $('pre code').each(function (i, block) {
                        hljs.highlightBlock(block);
                    });
                    var scrollHeight = $('#content').prop("scrollHeight");
                    $('#content').animate({scrollTop: scrollHeight}, 200);

                }, {
                    token: "kltoen"
                });
            });
        }
    }


    function loadPage(func) {
        let next = func();
        if (currentPage && currentPage === next) {
            next = func();
        }
        currentPage = next;
        $.post(next, function (page) {
            $("#form_context").html(page);
        }, 'html')
    }

    function nextPage() {
        loadPage(() => {
            let next = examplePages.shift()
            examplePages.push(next);
            return next;
        })
    }

    function prevPage() {
        loadPage(() => {
            let next = examplePages.pop()
            examplePages.unshift(next);
            return next;
        })
    }

    function createConnsoleInfo(logMessage) {
        if (logMessage.type == 1) {

            let msg = `<code class="sql">${logMessage.body}</code>`;
            return `<p>${ getLabel_sql(logMessage.timestamp)}<pre >${msg}</pre></p>`;
        }
        /* else {
                    return `<p>${label_param + logMessage.body}</p>`;
                }*/
        return "";
    }

    function consoleStyleRest() {
        $('#hql_demo_console').attr("style", "margin: 0 1% 1% 1%;");
    }

    $("#connect").click(function () {
        $.post(webctx + "testSubmit", function (data) {
            console.log(data);
        })

    });
    $("#sendMessage").click(function () {
        stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#mesgInput").val()}));
    });

    $('#prevEx').click(prevPage);

    $("#nextEx").click(nextPage);
    $("#clear_console_button").click(() => {
        clearConsole();
    })
    $("#up_console_button").click(() => {
        $('#content').animate({scrollTop: 0}, 200);
    })
    $("#down_console_button").click(() => {
        var scrollHeight = $('#content').prop("scrollHeight");
        $('#content').animate({scrollTop: scrollHeight}, 200);
    })

    $("#left_console_button").click(() => {
        consoleStyleRest()
        $('#hql_demo_console').animate({"margin-left": "59%"}, 200);
    })
    $("#right_console_button").click(() => {
        consoleStyleRest()
        $('#hql_demo_console').animate({"margin-right": "59%"}, 200);
    })
    $("#rest_console_button").click(() => {
        consoleStyleRest()
    })
});


