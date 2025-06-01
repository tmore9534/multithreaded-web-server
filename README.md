# 🧵 Multithreaded Web Server in Java

A custom web server built using Java Socket I/O to explore single-threaded, multi-threaded, and thread pool-based request handling. Load tested using Apache JMeter to observe server behavior under concurrent requests.

---

## 🚀 What This Project Covers

- 📡 Basic client-server architecture using `ServerSocket` and `Socket`
- 🧵 Single-threaded server: handles one request at a time
- 🧶 Multi-threaded server: creates a thread per request
- 🧵➡️🌀 Thread pool server: uses `ExecutorService` for efficient thread reuse
- 📊 Load testing using JMeter to compare performance under stress

---

## 📚 What I Learned

- How threads are created and managed in a server environment
- The trade-offs between single-threaded, thread-per-request, and thread pool models
- How thread pools improve scalability and resource usage
- Using Apache JMeter to simulate real-world load and analyze server performance

---

## 🛠️ Technologies Used

- Java (Core, Socket Programming, Multithreading)
- ExecutorService (Thread Pool)
- Apache JMeter (Load Testing)

---

## 📁 Folder Structure

/single-threaded-server // Basic server handling one request at a time
/multi-threaded-server // Spawns a new thread per client
/thread-pool-server // Uses a fixed thread pool to manage clients
/load-tests // JMeter test plans and results


---

## 📊 Load Testing Insights

Used JMeter to simulate concurrent requests and observed:
- Request handling times
- Thread usage patterns
- Server resource bottlenecks

---

## ✅ Run & Test

Compile and run any of the server versions:

<details>
<summary>Sample run</summary>

```bash
javac Server.java
java Server
```
</details> 
Then simulate requests via browser, curl, or JMeter.
