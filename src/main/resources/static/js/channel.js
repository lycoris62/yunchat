const channelName = document.querySelector(".channelName").textContent;
const nickname = document.querySelector(".nickname").textContent;
const message = document.querySelector(".chat-input");
const chatList = document.querySelector(".chat-list");
const sendButton = document.querySelector(".send-button");

window.addEventListener("load", function () {
    const sock = new SockJS('/ws');
    const stompClient = Stomp.over(sock);

    const connectCallback = function () {
        console.log('STOMP 서버에 연결되었습니다.');

        stompClient.subscribe(`/topic/chat/${channelName}`, function (message) {
            const msg = JSON.parse(message.body);
            const li = document.createElement("li");
            const text = `[${msg.nickname}] : ${msg.message} (${msg.sendTime})`;
            const textNode = document.createTextNode(text);

            li.appendChild(textNode);

            chatList.appendChild(li);
        });
    };

    const errorCallback = function (error) {
        console.error('STOMP 연결 에러:', error);
    };

    stompClient.connect({}, connectCallback, errorCallback);

    function sendToServer() {
        const data = {
            nickname,
            channelName,
            "message": message.value,
        };

        stompClient.send(`/app/chat/${channelName}`, {}, JSON.stringify(data));
        message.value = "";
    }

    sendButton.addEventListener('click', sendToServer);
    message.addEventListener('keydown', (e) => {
        if (e.keyCode === 13) {
            e.preventDefault();
            sendToServer();
        }
    })
});
