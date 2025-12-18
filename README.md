with java 21, run application:
```bash
./mvnw spring-boot:run
```

modelcontextprotocol inspector web gui:
```bash
docker run \
  --rm -d -p 6274:6274 -p 6277:6277 \
  -e HOST=0.0.0.0 \
  -e DANGEROUSLY_OMIT_AUTH=true \
  -e MCP_AUTO_OPEN_ENABLED=false \
  ghcr.io/modelcontextprotocol/inspector:0.17.5
```

http://localhost:6274

---
curls to understand the MCP protocol:

get Mcp-Session-Id: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```bash
curl -v --location 'http://localhost:8080/mcp' \
--header 'Accept: application/json, text/event-stream' \
--header 'Content-Type: application/json' \
--data '{
    "jsonrpc": "2.0",
    "method": "initialize",
    "id": "random-id-1234",
    "params": {
        "protocolVersion": "2025-06-18",
        "capabilities": {},
        "clientInfo": {
            "name": "My curl hack",
            "version": "0.0.1"
        }
    }
}'
```

```bash
curl --location 'http://localhost:8080/mcp' \
--header 'Accept: application/json, text/event-stream' \
--header 'mcp-session-id: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx' \
--header 'Content-Type: application/json' \
--data '{
    "jsonrpc": "2.0",
    "id": 1,
    "method": "tools/list" 
}'
```

kubernetes lab to integrate n8n with ollama and mcp tools:

https://github.com/uaiso-serious/infra/
