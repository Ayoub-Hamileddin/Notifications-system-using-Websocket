
let StompClient = null;

// Function to establish the WebSocket connection
const connect = () => {
  // Create a SockJS connection to the "/ws" endpoint 
  const socket = new SockJS("/ws");

  // Wrap the SockJS connection with STOMP protocol
  StompClient = Stomp.over(socket);

  // Check if the STOMP client was created successfully
  if (StompClient != null) {
    // Connect to the STOMP server
    StompClient.connect({}, (frame) => {
      console.log(frame);

      // Subscribe to the topic where the server will send messages
      StompClient.subscribe("/topic/messages", (result) => {
        console.log(result.body);
        // Parse and display the received message
        show(JSON.parse(result.body));
      });
    }),
      // Handle WebSocket connection errors
      (error) => {
        console.log("Error webSocket" + error);
      };
  }
};

// Function to send a message to the server
const sendMessage = () => {

  // Get the message value from the input field

  let value = document.getElementById("text").value;
  // Send the message to the "/app/sendMessage" destination
  // (handled by the @MessageMapping("/sendMessage") in Spring Boot)


  StompClient.send("/app/sendMessage", {}, JSON.stringify(value));
};

// Function to display a received message on the web page
const show = (message) => {
  // Select the div where messages will be shown
  const div = document.getElementById("show");

  // Create a new paragraph element
  const p = document.createElement("p");

  // Set the content of the paragraph to the notification text
  p.innerHTML = "notification :" + message;

  // Insert the paragraph at the end of the div
  div.insertAdjacentElement("beforeend", p);
};
// Automatically connect when the script runs
connect();
