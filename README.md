# DCCore
## Features:
* QuickDeposit
   - Shift + Right Click on a Chest to deposit your whole inventory into that chest.
   - Shift + Left Click on a Chest to deposit the item in your main (right) hand into that chest.
* Spawners
   - Must have certain permissions to be able to break and place spawners.
* Muting Chat
   - Can mute a chat for players. Staff members with appropriate permissions can talk during this.
* Custom Broadcast Message

## Commands and Permissions:
>
* Quick Deposit:
   - DCCore.quickdeposit.use - Use both of the QuickDeposit functions.
* Spawners
   - DCCore.spawner.place - Place Spawners.
   - DCCore.spawner.place - Break Spawners.
* Mute Chat
   - DCCore.chat.* - Include both of these permissions.
     - DCCore.chat.staff - Bypass the mutted chat.
     - /dccore chat toggle
       - DCCore.chat.toggle - Mutes/Unmutes chat.
* Broadcast
   - /broadcast <message>
     - DCCore.broadcast - Broadcasts your message in this format: [ServerName] (message) -(sender).

* More permissions:
  - DCCore.staff.*
  - DCCore.broadcast
    - DCCore.chat
  - DCCore.chat.*
    - DCCore.chat.toggle
    - DCCore.chat.staff
  - DCCore.player.*
    - DCCore.quickdeposit.use
    - DCCore.spawner.place
    - DCCore.spawner.break