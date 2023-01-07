package com.xiii.libertycity.core.displays;

import com.keenant.tabbed.Tabbed;
import com.keenant.tabbed.item.BlankTabItem;
import com.keenant.tabbed.item.PlayerTabItem;
import com.keenant.tabbed.item.TextTabItem;
import com.keenant.tabbed.tablist.TableTabList;
import com.keenant.tabbed.util.Skin;
import com.keenant.tabbed.util.Skins;
import com.xiii.libertycity.LibertyCity;
import com.xiii.libertycity.core.data.Data;
import com.xiii.libertycity.core.data.PlayerData;
import com.xiii.libertycity.core.data.ServerData;
import com.xiii.libertycity.core.utils.LagCalculator;
import com.xiii.libertycity.core.utils.MoneyUtils;
import com.xiii.libertycity.core.utils.PingUtil;
import com.xiii.libertycity.core.utils.TimeUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

@SuppressWarnings("SpellCheckingInspection")
public class TABDisplay implements Listener {

    public static final Skin CHAT_BUBBLE = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjYxMTE5OTc3MCwKICAicHJvZmlsZUlkIiA6ICJhNTkyMjkwNDVjMjI0MGUyOTM0ZjMxZWFjMzNiY2IzNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTbHVnRGVhbGVyQWdhaW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ2NDVhZGFkOWRlMDgyNDc5NTVkN2UwYjc3MWJiMWY4MjZmOTI1MWY2ZTA5MTNmYmJjNjdhODAxYzJjMTU0ZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "dZKZoFGwqs7WQ2zhQNiN8UEMRVdRNOr1kzhYBVGutOledr7VmxGCIfKNNil4Gs/zmm8+/jCZPvJn0/39XAUVuCGw6Mhr4+2Vatz1ivpC9JfT4Czs8op9JsVcYZ/pLh5j/CxpgQ3/xn3tYuw09aHwZPDZW+FXt8zMYKNzrXiBQrD2jGYe9FHSWmwghYSHAx7DVbg20B5ajpvGejMzvA2PBSQFe7YX/7umpnIXrst8LcDP6EdhlmNgBZ3x51H88cn9QqctXjPVwweW81B0ufHOTrbncAGFgCUykfny0dlcYYdiulm0dTNcSFAEzqbqNfNN6+XveEiss+POyQt+g6Vr9kfMokgk51x+gtBHnre7MAHBTfcANR5A1PMfQOrFJUCFt42MXYp2Y3iHtYAho86jOlKs/nX2rSS9uOJ5ov70aIYyNNe+5w4kll5tZmaaE16fqCY0oKOICzAzOWe12tOFPTlpLyM1+2UBdwGjMUaJmzG6X0YiS8JvH73HuAmVM/wQuZ/WRBnOEvcqZjUPyqkCvP3vNIqecPeXZvdDz7IJJ0Q2/iRZdE4m/mnbvItcpeZvCQeH058AHqGpYY7Ei0SwdU568EasShYwoOqgruC0NP2O7dAo/pQjEQiEZnGViDUFUaz0bbXpIvnDuBW+eyuEenWAN4ASVgtNSoy/F6093Bo=");
    public static final Skin QUESTION_MARK = new Skin("eyJ0aW1lc3RhbXAiOjE1ODU3NjY0NDM5MTYsInByb2ZpbGVJZCI6ImIwZDRiMjhiYzFkNzQ4ODlhZjBlODY2MWNlZTk2YWFiIiwicHJvZmlsZU5hbWUiOiJNaW5lU2tpbl9vcmciLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y3NjliYmI5ZmIyMzE2ODA4MTMxZTZjMmQwMmNlMTRlM2FhYjY3NGRlYjRiNTU4YmIyNjVjMzE0MWQxOTliMDgifX19", "uYbVRBJBsjp2tAh/BsWARSg9UXcAkFvkWZhBm3ly7Xlvv1uQEI9S0tyb+ikm7gncYS8O2n206Dftyb6+UfAp04jJ23mWRJlIHF8zCuUoN8rD7wQelrhRacoUEsgyfNZb/zNXZh2F12JpGTCLWWhCy7C3fItGaFohkKTKBaahv9z+hVvO2bOOwi1LbxYzm1i6tWpz1S9eoy5TqrGLoRI4LjGqiY7QaQmSZ2DVRg8zcWLySjd+Zii1tcMSSqd7JE2dAPvt4WOltdlgZck0X7MR8Ky9pX3u4YJx022JCrytTEnGsMA/nLTyclFtFT9TkWpvx44diiv6zZGbWIIVPbhTl8gOZ12+5ereJZ/2gUduawDjTnc3ZU2Hk7CTKfsMRnz/yUT7BiAxVLdkQtdE5YL3CSuXVYDF/fO+B9heyUkIM7i8SkdtOKrFkZcrEJYsbvwkDIW/j8E3Pwa4vJKzwcM5HMqnSkMs6RHgUO2Ngn2AT7L8JW32QhTvLjUWJeyeEvSRUjMNozju6RS5tljQZXwZkrYGJEITkx8u31vS/w/gbXMqv7kN/GsQi8+Twwbu2h7etExRxnuggd41SIZWvrcne4Qv/M87k9OYER6FAlT/JI9qKF29LnDERUINVMTwo2Nchw5pXRf6YlsAFSiYpWFgGC7C+y9BXkswd4WyjRk2Ujk=");
    public static final Skin FULL_DARK_GREEN = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTYxNTA3OTk4Njc0OCwKICAicHJvZmlsZUlkIiA6ICI3NTE0NDQ4MTkxZTY0NTQ2OGM5NzM5YTZlMzk1N2JlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGFua3NNb2phbmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIzZTJhZDRjNTllMWUwZDYxMTQwNTZhNDYwMmMwNGMyYjg4ZmNlZmE1MzI3MzgxYjI2NzdiOWMyMGI3ZGE2YSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "K3qpc1txiumAIyTjAPiG0I+doHb8K7XwTuIMVb4OllCkJlREzybd2jTsWPNuxQRjeyOSHwzhj5ROtkoH6YyKhRGip6r20Q1js/ZVqRFZ8rktmLHY5AqDgoMdXXcSstrPLhta5SirCKPjL68HaOmjNDmT2oHajx/6UD1DMRDmmQs5j0DoJ9OUNavbv8WRtQbKh/7Foe4Ik1Itsmhx+oCUTliqO4BQ1moqzHq+cZw035k2K+PSp3JndT+5SnDGNArXs4qCPnBTJjT5qq7MwC4/jGzWJI0x/o6j3DD9pQ4+Lxel5P1bfeC/9PaVDTl1tlcJ0bKoyOgVygW3/jYzSBbYyDziThFnckYfCYmAognHQvEQL1/glBEXQK2jpi/hsg4xq8c2eGmsBpK3K6Vatg877CZkfJAS5HAovbT64dVJijYNlcB9Mqw1L+cTxGkb+W7mEIXNGXMATyHZaxIOrQmLXRSGvFCiE2WB0fd6ytkOT5CxIXFxJwtdunsMIhzUclyjmys63LWHQaLjwOWeEnnhm3SgQcY9jjvUMKjdPOD6vPWwzWKYhnp7AY1yDsGi8hgU4J1JRD2jnYdziMBTw9QBikV3xhmXCatsUVxqwkRNR5BohyZ2EFIw0ebvWrlfcK/gtnKV0DR3JoLlEimKKk4v0BIhqd7uVbS5pmNR7/zR2wo=");
    public static final Skin FULL_CYAN = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY1Mjc5NzQ0OCwKICAicHJvZmlsZUlkIiA6ICIwZWQ2MDFlMDhjZTM0YjRkYWUxZmI4MDljZmEwNTM5NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJOZWVkTW9yZUFjY291bnRzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIzMzcyZTZlMDRhMzc1OTU4MDY1NjkxYzU4ZmEwOTA5NDIwNjJlZDY1N2FmNWNlYmMyODIzZGYxOGNjZDI3NjUiCiAgICB9CiAgfQp9", "Swn81VouqpHhEPKbjHj7FXO4tuP3/1oPRLya33XOJJfWP2Zd2tuEDVKZplbzuuUzEgHlnl6eqHVZ9RUz3d2lQQ9sTbu4a40HJ00I654oQWNkhcGI+h1EamcpxWszAHHLeusM2TARiElhmLS1nFNuA9MvJR35LefJMMAZ2arHeLmdFjVSLBR6npts/6pfDY14ox6ADMFnqgRYHGryWfmel1AG0ztfXPoWXrg1ZfIRBSVe5WIUA375WEvYzpoHnfHvISiF7s7inCWh4hCMzqbnfhAIQnNk0bKMywemvP/FoI9jrl4kJBrxXlxkY2I8/1SCxpauwkeVYp12KQH7U24QGKVps/EW2Dimfgt8aW1/ZQZzeeHlLkM9xjXkyUE8KsV32K9W5o44nYAlo2CqOp9N/WmLFhL9ORnJgiYSAjYM0rWKdSNwVbfbaecxlynQNhSK+hkAg6O2xuPJhADSUEW5S2hL7xDbmRwDf7bPlujaPP1M9XhKwGV0Jj9M6HuV5sCZCk8sYyg27N+ySWq6JO9Dnvo9/FtV1+LvO6HaVIyRJ71kBW6fdVd4XY+EL1bYpJrOyuyUo4I27TKbVG+0WwOvpJgcY0cPFVKbOOKCX6T/9lZvhkyBOhlWdapJv4KcxekqKjLQwpx7SOHyeTGQvcfJitFXITG9tZkb6CFLO3K10zQ=");
    public static final Skin FULL_GOLD = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY1MjYzMjc5MywKICAicHJvZmlsZUlkIiA6ICI1MTY4ZjZlMjIyM2E0Y2FjYjdiN2QyZjYyZWMxZGFhOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJkZWZfbm90X2FzaCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hNmZiZWM2YTM4OWFhMjJlYjQxYjJhN2M1OWFjMGZlODg5ZGVkMzMzMDJjNGRlYWU0NTgyNjUzZGRlZGM5MWVjIgogICAgfQogIH0KfQ==", "BANObOFUdOdqYXuzTbm5PwAHNCiwPXOyCRZt3X8nzeJkcSJSws6G+vhGKVcDJrvGNaIaqqw5aBFh9Zh5ub9gI5J1P3RpoxJS4wYR09kaQp32tSabbWWXDOGuy0VR54M+pSiga5Lfdpq0IQ+8IoWULzbriSgJgWucRof9whvuQDrAlBaR2PpnDd+bSb8oqTWxD8rb6Bgwab0xsTNo0JRhSm9OXUyrII8jYwb33qZL0V2kuSOe83UDU9KD/dBvcY4Wf6CdtX4MKDFiI6SaAIELRml9z39HzY3erFSSUUBGEOBaEYzggNWdLRnBne9yrIpGx9xH4iGaFeoEu4LhFFZCCI3ZqdxZ6r/6Vf4UTLgr9/yU1eDq0pvKL6EXNHYFoj1VWi0auNYYYZ9CDNLY5VZgLwIbsV2jBaeYCFAm5kpmbSAuA2j5dlS4c0r1DSnqtcehr6guzUAHG9EvFA178ek4KulGxt92y2u9JZWvmFOB2zriaVXNhc90JsYDSgvaU7v4E15z+iw8zXtTSt1QA7juorVhKZp1bk2+vm/dzFqFl5e8YaekrTCeiBzAFWGw3DxBH6IStwu1nFGBx12JfOkRNYqLfGjKC+aC85i4TQj0pR3Q+NWKqons9xd8syNvzn04MNky/VVTk78P3uPXuVlh+MtXOkQVRsEYITpzERei0Zo=");
    public static final Skin FULL_LIME = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY0NTI1MzMzMjYwNCwKICAicHJvZmlsZUlkIiA6ICIzNzNiZmY5NzQwMmY0N2IzOTViOTZlNzc3NmNmOGFhMiIsCiAgInByb2ZpbGVOYW1lIiA6ICJjdXRlcGluazczIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E3Njk1Zjk2ZGRhNjI2ZmFhYTAxMGY0YTVmMjhhNTNjZDY2Zjc3ZGUwY2MyODBlN2M1ODI1YWQ2NWVlZGM3MmUiCiAgICB9CiAgfQp9", "ZNymefoj/szTJt4VIw+ud5VX958KmXxi0QbOY5TTm2MCB5hidrzSXcWWo+Wy6FJhLrQng1x5e8b2VhyFeJM3LV8VrzWJWzRFiu1wZAed+6GljJwaoBA7/SuQyUnrLj368VOOTg9EcgEKhqRew0JwmVhNhfIeqL1OewTt2uUtm+uO/OIqUHqWIpMfgifppxrK1D0FG+i5E6niGrOI8n8t7yqtPZLx02fOkLssL2/VY7Q6Yrqk6BYTYiqmvvr1hvFsz2JOd43ZEoKTFyDbbNkZJN515H8VKLUB3oHqSFqiRUDNSBRsNomHiWIQBvntyvEujO0f6z6FoWyLJ9qVkgbNF7admYu8hGx7zJoaRWtbOZNgf8o2MPoWZAeOeY4eC4F9XS6SZZg6NFZ5s6On9ZI9MrGfff9Te/d/uxP7fGblgdNDqfH3Hr6LP9XFQH0jgn3+/CX+sr/zVqFL5YmAszOjyGVwBxAY8HqPYKvGHdmrC/lkl8sfqN57qeai2Y6RhrPY3TpbXtEYkCY0G352fNajj0tCy9hQjzBt1sgFUeEKgvdHq0xZ5wpYPRKizFzO7VhxJhJqOJFS9KaDrxRIII62eXVbTTkiVzAHXsq3uBs6FD/kQy3kDjlBU79CftT7w7GmsC9Kjafeew7f4RRQ18CYENyjmKx5zOQkFpcRc/6Nbjc=");
    public static final Skin SERVER = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTU5ODIzMTc5NzQ1MCwKICAicHJvZmlsZUlkIiA6ICJhYTZhNDA5NjU4YTk0MDIwYmU3OGQwN2JkMzVlNTg5MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJiejE0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIwNjQ4YjYyNDA0M2QwZjI2ZTQzZGJjMDMwZTgxN2ZhMmU4ZDliMzg1ZTRjNjI5NDU3MmJhZjcxMTQ3Mjc2OWEiCiAgICB9CiAgfQp9", "CarAI4njlIY0YhvoW9C7uNYpsw5g0XFYY+H2Qf0qie+yaZ4v+ICljNWeISvx7PbUH8YgszNXnORimea0QXcEPdW9UhuHqckqWJJH8eLKC0Yku1gO/WGe1AdqZQ7rEtDGLH43zOloHkddwu7XOwRY2rS5nz12QRUHAyzgixAcUFL/nPQyRCyx8lpp4pVk2xWmweW6n8b1EZkP8e6aujFj679tjbk/IQiVqkobr1HUrbXGD7RwU9vJrl1PW6UDTapoUJ0NX40y4FvdjeN8OxyEGfyp4Gs8JoPEBFKwOgNyBZijFJIsJo12sKsNqy/4fog3LcYjYBOzwam6FfZkS9c0Uk3d/RrLvfq4doOai8H5HADr5vhwO/2VSmDOqNmaiaYcyZw5tMwbffH0E7ToHkZXdG0yyldX4IhDsNebHD0CSphSiIShcibh9+zR/MfHIsEnHtpZ/s/1bFHTaSm9l6oIsqMklrcPivikz3trIB9ZOcu0SpvoPb33imCEynHyiASejXhPCDhnDYgruVstkFKSVrkPCEnXWOSleSz3yWTbrC0Kc4D+QuIxueuQO4mCobwuI4WjFBM81SK5wxVG4d4ASDsQ5bsNQOSK7yZvRT1pHbbhZCyhPrMsa4QE6WcNszpz8+9HQ/sTqnPSxJ66KjKfQKJ3ApTuGGTUNrTEqYOYFsc=");
    public static final Skin SIGNAL_HIGH = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTYxNjg0OTEzNDk0NywKICAicHJvZmlsZUlkIiA6ICIxNzhmMTJkYWMzNTQ0ZjRhYjExNzkyZDc1MDkzY2JmYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJzaWxlbnRkZXRydWN0aW9uIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzMxMzVmMjhhY2YzM2UwN2E4MDFjZmNkOWFjNWY2ZWQ2ODliMzBhZGIwY2ZhNGQxYWFjYjk1OWU2YTYxMDY0NzEiCiAgICB9CiAgfQp9", "gmgVpZOW/gTAdVxu/9xDkZlinD4vED2vAI1n/T+9tJyHhAFq22Su004TwXOp1V5Ae2I/ZX83lfaEJ3qeROCRh06gi7zm02JeWL0uZQyjJfCJqVzwz4IPDdvtyPdny/uEQvG94XiiV4csaEqL0ygydzGuUWf424sXqCKcmwE8ZdRh3XVGQd5PYNHC/zSWLymp/C78pUzla0XDiCwk/B1mRhzbaW8Hv/gYYKsRnWfWBGF9YAgxsQjL2DswJ+Y3/arqOf9UbcrlXmuJ9kZyXWOWKVtGAHAuMY50VVn3ohRhA1MSOIiL7L3mv2BsWh3/mM5x/b0D8USV/NwjyIO5W/onVO3KuLeB1mE+lFV+BFB92djjGIO9qjKYVIFCeG2uLDeEg4+ysXL6/mLOinvsHKynuvOoQ1oXzbXHElI8hnNiWgUI78cGbLRIIWSm4gaUVVWk+dUCgnwuxsdoHtncaS8nf/5Mcr+LroZBXrxp/dEJ3HrcowrjWZ7Tc/GvGZcrXKkpYB05lvkZW6OUk35QW+dlunQdGNa/4UAGwOK9tIlVtBbbbaUxrBzrW5q5AxxV54zhhg1tHe3jgm4jb9NvEJoubCl838DY9kawnvjwkrrH24QWI5XA2tuE9kQRiFwO010nFQxj1XG/oFddceKc1JchvNs4inm0+c5thdHpS6/zHFY=");
    public static final Skin SIGNAL_GOOD = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY1NjIzNjUxMCwKICAicHJvZmlsZUlkIiA6ICI5MWYwNGZlOTBmMzY0M2I1OGYyMGUzMzc1Zjg2ZDM5ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJTdG9ybVN0b3JteSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iYWE4NDUyZTY0MzI0ZDhhMDExNDBhNDY3MWUwYmM0Yzg3Y2FkMWIxNDAwNGRiMDNlZjQyOTk4ZThjYWE5NjM2IgogICAgfQogIH0KfQ==", "MtcI74pIcips4yWs9ONvrfwz5w6OkdPR05vXbjloYanSwMXn5fNnx4QehQ3p6XFoMZN4hjUCgPWKIgdJCtBJSzDIuNh9uicG5VKseAFqERBmLr2enPfBop58SbgzgUJymSp1Xudfq/w2maFXCS4WpJXoaCo8J4xntKxx+st374h/rfxptANNN3ymdArj+1YLLal2HGJgO6H78BN4BvjcPoKOM4mOXrCHbjxheGoOiOOBLOJWQR7SpTemyOWJwTXuGRmWD7AHveFMoAFxM8oTKmkjy1moUly5E92GNj/19l8Ib4XdIJ0W/DMiHagOz+2aLC6Xxbdsy0bMQH5HvNIdccw2iekNq/2vi9433ir77hwSEpIl5PNBHELv7srmY68ozIFPR6kyvW8X14YroWdDbO6703qG6MwHkeSRnTa0i6nPdMp2WmW03xeedRfBtvIizNnP3RHBAq775IsjTSdo2+b9HZOzmxZ2ec7dF+unEAjnAEtBYDxDHgch2RwYzLkprSot+wlKYlWnN+94bEL7ULyawOFO//F8eH4yWytl+t5RbiZWZm3NfRkUpvllcLQr+ai04lqhyz5af2B53JO+k/IeQecJjQRucjHmp3tc+PBb5pDClheYD/bD2xh0zzkWuijIK+w0WsL6Q5W8UO1J+ovOi11WDwPpQEsCRurLIH4=");
    public static final Skin SIGNAL_MEDIUM = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY1NjI5NjkwMywKICAicHJvZmlsZUlkIiA6ICJkMTJiOTk3ZWI2YTQ0ODQ5ODJmNDE1ZTI1NzFlNmY4NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJUd2lybGJlbGwiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE0MDU1ODljZTg3YWRhMzA3MDBlYjRhNjAxNzhkNjc5MmRkNDMwY2Y2ZThlN2M2NzRjMDQ2YjQxMjFiOWE5IgogICAgfQogIH0KfQ==", "FkUCduqNG/yZJawAmAuHVvKw7YlIyQUV5sb6aps/OhNYTUQ3+jb2ck6UEyUFajjdLy1oeMgRX5Pmf1LpyMfMX8WZehnD+bYQ+o5/SVA47nuY5LPnBLk8Ra36EPgIGs23XcqgEVNVbWeZaMkogHmhWZN9Uok8+cGbftaBIsMgekoPbrWlYA0FOyMDu4Q5KEyvXNpJ0Jvey8pccGJwUAL0x1z5Wa40ZeyypvpOEyuHfd/JrTDk3Z8ndgTNsFxanCDOC2auF9Rq78N30UOeEqYeE5XMg13ofH/mUISramgrmCuJjYtmXC2Ekc3zU2jpVz4y/DpdTZoBNoEDOeZMOYsfvu6r8XbOuRe4wLDo3E+7zkPqKeg4LF78J8uw0maatM9AV0UAcDPJ1TgCXt6n5AGWs/PT5ZzVPEhegI3PZozuOpUD+mutknLkW+sDKlshGu27WFrp9yN0v9Tz0iphl9vaADCgtnvVRuTklc+ntpdzaQDeiSZIzDnAkkvSbPnfKNmlsZCbgn/KlBiOfTlT679/VQs2WXyat3/ujI7r85TqKAHj6GOEG+fPVkJ9TQRmD3KGd6W1r6PMyjmJdZC58JH6LoV54tO/mfdKKAtkhhNhcsRNtk7uflu9ppd2qniOzi/lhNuGj7IRJMdBYyRZxivkjiG1ztKjquXph34eyWqaObA=");
    public static final Skin SIGNAL_LOW = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY1NjM2MjYxMCwKICAicHJvZmlsZUlkIiA6ICJmZDIwMGYwMDE4OTI0NzgxODI5OWIzZjE5Yzc4Y2E3MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJ0dXNnIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY0NTBmY2Y2ZGFlMDI0NTA4NGM4NDZkMmRkMTJhN2UwZDUzNzEzMTRjYTMzYTAyNDYzN2I2NmUxOGM5N2RlMGUiCiAgICB9CiAgfQp9", "bni1yAMo+qy2NaudCZYfdNJNh+WGp4ecCwgrUGXJuJcP0UGhmXPW3F89yiMtJBJ0e+cPZdWCr8wPxlf1vxuntbIo8faSMPZCEoBX59iZJ16q+OmU3N59FVXZjOEJvXb6uxFgOwCycBgi6t0z2uUCnlcdQy02582Xd8P7bAKrph/XvP7ubjX6ph/LVCYu6DZEulT/mdlEgZ3x4bcw/aZ86zEnQn+7X3jmXyUkgki2Q9jVB8esLtoOKoBsI/fPhnSmnGwcTFe6EbjKN4x1taRjyxbEU6bEkwHs9HPtm7DWelmmHvqt3miSXJhl/gLWfwweOXjL28VW7gSxMAQK/39uVxOAfmd/gYoW+xapW62mLYjT0Xu4lorIkJ6nTfx8XvSTONlgreEM87SYx0wFRrdOCc21j7UpL6Zu/JHPUfo9pUL4EsR3DJPnJGcTvMeVIbjmjGxXGxF61/vqPhRybQ03mYmd4Krd3ccBzJUYFbklKnntOZ777g3wNLZ/rkzupjS8OZRE43d/VLVnhHJXSSbn4jttVmD5KSkC/g4bN9xIijn8UFdr30XehWyl3N0Yo7923Nsv9Y6CraPNChdnnTS2VdP2XMPFF4xC024CtcDBV1UDPdQQnNU8pDPYam4qvFCTNUwbfJsatutnkPX6WrJLogZ18PZpucrjRd/7K0YYnLs=");
    public static final Skin SIGNAL_LOST = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY1NjQzNjI1MSwKICAicHJvZmlsZUlkIiA6ICI1N2I0MTZlNjJjZGE0MTAzOTRiNzZkNmNkNDA3MjFiOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSME1CSUVTIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I5MDQzODgyNzRiY2FmNDgzNTU5Nzc4ZTY2ZmFmYzZmOWQ3MTM3OWEwZmFiYTBkMDk3NjgyZGEzZTAwM2M0MDYiCiAgICB9CiAgfQp9", "Zl35g0rxIlotgiulNzLn8guflrvAo1EygdfvqndzD6GjM53m5u2RcRXKCTjBiJbE4HJhdHd1ZhU/D99YrS+JDqrs5aSQcE4B5a06+yRcx7UrqkttGOj3qnZMVG93qWlMW8tzN9kaaChyOZeqfBowE0CxLqcPdUo0ntprIxTntvTzfyn2aUHCN/5JWUTWXKXoqJxIS4DgLh86i974NXRBDi4kxeI6WlOBePsd45X0XyZ4+U0VZEAZaSHtD+xIZ1vOYOTo0GgLbMeaC68LqdbJ2TUAdTAZCGO7CyOiLU0gRFyveiMIE2e8fTtCspgE64kPqyKm2LymwWwzS7BmWBy9tvKI3/nAaIqMZXUReENXCg13SUz75Wk4Pc2K14j6DsoKqxN66JxNoTb4vWRZGNg2ZCpOuxc+IPPXSqSw3QDSZ6S8tdiE2W/7N+E2AqvaB3E/byr+Odx0B7m0euaO6i9PLjP9j6r6Q8kAJ5R/bweKf5pIOiZW0Uqlfm6zvWcPg4y/xJl+XmJaOwoaSinfpizkK3ylkv7CdDvS2/+Z9ewvkr+gUvg6wjXVMiCFVfgAd7eAH0av3bzEGg8dKu3qE/lpPT42XrmbZOXIx/T+IkweXoWarn+odcK3zwx3LM/fMUqnHQQgnvMNFlwlaBzkgAb4HwJcRUOv3bjVTax6b+OKaPo=");
    public static final Skin INTERNET = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY4MDk4ODM2MSwKICAicHJvZmlsZUlkIiA6ICJlYmRhZjExM2Y5ZTQ0OTJiOGM2MzUxMTVmYmYxNzVjYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJhbmRpeWFzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I2MmFjMTczYzE5MWIwMWE3MmYzZTEwOTRjNTVjNTUxYWY4ZWViMWE0MGVmZDUwNWY1ZjU1MWNmODc3NTVlNWQiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", "clMQSKTV5o4sT/ss2dFIqWz2P+GdlR+n65liF0NLRAFDALrCdYl2IrdGi5LYXoHYoEFIzDAQ/YO8XI3ajJv7jZ011FmD4gwv0uLDV6BnaKSbRsVevR6f4GsOLbdHPNjTTxKmXlMvUN2o2lXfeuCnp7/62Xib2YvEysSkbiHmYv3wHLIdYbE/UEXh9CAM65/L5tFbQ4V57LGwk2adio1vBbIc6yURCoFQh0rjBQJhycX4VkDr2nL+YPC/QIq7aV44UY0TosPXFIORzCAnOQ7RZXku0w69hP1E2yrrxiwBHG8p1Wc+MP1CGBeVaiqnI3uCiaKxYOjQZCM8axLc1yR5BtR7zHwTixpJAIP+CQMJfEHrVWTB2kuKhekUU3G2u15yUVJO9VlxprN4uDCTy6R+J28O8rNMRycZKrbusxXj2f8V5DXguWF3YxWQ8R6l6FNxH9a15dFiaoYMz2oBZFQpsrHCgLaV6kbEYIWzODJIsKYkpdoCANSR6gZ2meEaakeOJ1LmkooLJ41I2+O+5L7A0fFt9ubLzSa3f6FpR5TAHIMwvstmi/jM3/kzcEuhN2knUXNWRNbjHdVZ70r0gCVW1ozn2RW6befs5H8KHQguKIRLysXx6vRA92+PIaaswy22/tmEDXzC6U4sHcrW/t/kr5W9VsrljzrDos3LXboAlW8=");
    public static final Skin DATE_13 = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY4MTMxNzkxOSwKICAicHJvZmlsZUlkIiA6ICI0OWIzODUyNDdhMWY0NTM3YjBmN2MwZTFmMTVjMTc2NCIsCiAgInByb2ZpbGVOYW1lIiA6ICJiY2QyMDMzYzYzZWM0YmY4IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzFkNjFkMTYzMWNhY2UyYjgyODQ2ZWY4MjYxZGExMzBiNmU3NDcyNWU0MzNmNzZkMTU3Y2U1NDYzNTRkMTMyMjAiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", "X0xnv4Vtg7X/1LJZXXoZQV4weuoa60BBNsycsPy5oPQMzjOVVaUw9MlyqvnLlZQdPR1O+gA6M47UVoK+R9GFxizIpt4Y1+eiJgQtivLZ7QuX+yODwyi4m3ZtZIgHQuxuhDdZ+QrteRpyzi5QoM1Kl8ClC73x9svOvPHiWeHOLKdDpfezHm+WsDEbwdGZ6FpNtVymIoST09leAQvFCujbM2f6kH1boj7XN1FZfqLGLdZW47zGhDetOCpRB/f180Kfr7FeIU88bF77M/1xUc+I14DX8zMavyZwDEf9sNquCU5ZhOOWM7gEbosDIJn2rKbWMITuwTW8nYNEf61CvRiCUzn+eYAs96A+vFlCc0YH2G2zgUPqL0qdTQDouIYQbDgWgdwjVFruWT+nEdTrikdG2GYMExuV7gaPQVqBJ/BgWcjIdL0zY6suciQ8CrVIqivMJy9xbeqM6O2KDPMnE1akvB6I0wsjBb7QGAKIITLwAvNvYMExoWpzVDJRhPBWEyboa5TUDdShQFBPW6v2b5LEePrc/RPhs7SObuU6pdjf5EVmIqhufPeahSKrgsYJIOr3NUqgdaLP6WrAqNdMY0d3E4/3Lv7l+bnYcW+W+eWNQG7EtA3YtKw2lt5UHJxfKCW8/+a3q4DKkygXZen/phIugdVMWX+fu6ttScqXZiWpixw=");
    public static final Skin CLOCK = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY0ODE4ODYwMzAwOCwKICAicHJvZmlsZUlkIiA6ICIxNmFkYTc5YjFjMDk0MjllOWEyOGQ5MjgwZDNjNjE5ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJMYXp1bGl0ZV9adG9uZSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mY2I4ZjA2ODg1ZDFkYWFmZDI2Y2Q5NWIzNDgyY2I1MjVkODgxYTY3ZTBkMjQ3MTYxYjkwOGQ5M2Q1NmQxMTRmIgogICAgfQogIH0KfQ==", "MHrSK6ypEu+ZGIb/yQXU9bJCZfE2os9yZRICVf/Nj85dPcwat6DHHgBE3j+76URb3BRm/DIgvLw2wYuUWXbaU2NUU02xwrHaCSLFH1ntocOw1pjKYnh6GQpZ8I4aO78Kp6HpVq0RXxua2lQmZWqrdVPnQC/3KCWu4vfxXyKUkllsuT1m0n+vjXYQqekEKIg78bmBrONawOSeinD5g4ebcgHDRM4J0HLtEbBV7wYs340ckPIFEjvdl0plsiQcBmzKooy7g2cSEQqTl2fdv/Mjx4902gZ4pVWN0h+Qy4Q1YYmfHNxkubtgUKw65YJOS3/mYhODGnfLaedY/0S1jp5tkLFf9GEasK8uYEU7QgWNfyWkYTyxY20v0uvPOull9yKNjCvB51aNusQ7wcLS29BhYu8f6bLti2e1un+vgdXZmAIMfLMsiwKjMRuD+HvQriRmbwhOOD7hbTNle9n2nqYske8GPOW1yO9V9sp56vd+0PCLx0kZ1cbVo5EV5/2Sa4oxLV6SU0Ii7MKlFZGgSVQ6p+v+gXIW0SYdrtoalz2lPRYAibiz/ZRH8nRcBF2ncWNjPdC9TrFhlR7G0puZ2fL/XPtvU0WSio/DTWs6d1aFBhJRm3w6zxqOyt0k2FQdC5Q2gpGQljtvML3HowVU3n2lKiYsRX+dY3gbRJ78C8scf7A=");
    public static final Skin COMMAND_BLOCK = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY4MTY1MTQ2OSwKICAicHJvZmlsZUlkIiA6ICJmNWYyNDcyZGVhNDY0ODJhYWUwNDllYjM2ODE5ODU0NiIsCiAgInByb2ZpbGVOYW1lIiA6ICJEZXRhaWxlcnMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGZhYTVkM2NmZWFiMzNlZDA1YjU2NmFiYTYyNGM3NGE3Yzc2NTU0NTRmNzQ0MmRlMDdhZDNhNzQyZTM2NDkwMyIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9", "MrEWkWC/92eYRM3JiZLg4uEZgjDJVfQKQqldxfsU5DEkXatwpUtY8pjRbm8LV8yxx6edtosgy//DbCYQR9wsVGsoYbHt0njjvkcDAcvW+No+ASw1CDyrw1AX883JFZL/ZuV4aGdSX3bsHIibntRpU002ECGLt3v8i+H3ZnvdNxtS7Np0HFYNU5Y1CZhl9y0/nmKxgRyQ+gPlocieH1knn3eGAQMJpPwjk9ZYI7bT0GBoEMNDmR7YcXsDwfqzdlSM7KnxLvMqhiExag8iloiL7NbrndYS3TMwsABzwIuRofrPy7KrQU4sn5uh23YMu0L481Cbzxfl+yIxhOwtSQkZMFbMLKNaLpMlpUighWhTXAaW0X3dSpV54OJHXgZAQ2BVHd57duHiEsKYObjuZJZSykoNo3KtFuBwkO4crsOIvdwVMlXiWFssUKHR191D+fueOrEIgj2MeYGgWGSDDAVlG6sPP7C0v83yi/B9ZqNvKN2KVgm+zhIZU+0qlEkKI2eG0aGeHLXjbrZDn6Y/S0/kzCNsGD08vYGVHt4k2N+lnWrEdGYnJ+iaPJir1yMYnV04dquhadVkqYaKWEwcEKpeGqmv6KV74E+RwYTawUyotjnA239hYb/bW445xpBg/amw/nbQAw+vTcrgy/2UE5yTieR42byCdAPxA+C+kUsxEYc=");
    public static final Skin INFO = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY4MzMxNjQxMywKICAicHJvZmlsZUlkIiA6ICJiNTAwOGEyMGJkY2U0YjJlOTU5NWZlNzY2MDlmYjUzNiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNT1JJU3hTVE9QQkFOIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIxODcyYmE4YzU2NWNiMTk3YzllNWY2ODhiNzgyNzFkYzA5NDJhYWE1Nzg4ZDQyNGE5MmJhOTcyOTVhN2Q5ZDkiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", "IMncaf8vtdt7HMdnmN2uOJguW159x0JrMq2PCgQh2PrmUJb7lmyvM9JuFDmuOPudgc8E1Q8GQvIB/M8ReKhcRmERV0WqGywf60ht/raRVG05HGzkoPp8WvkD5StLjerZTkUec9PzRVIHBx/BXHl/JFZc/RUz5ZxHoNJQTN0GeraKP0qOe2eJSJhrwTIquIYOYIjT5GJhxXSpPuen1QXDnWZGxnHtbcPStdN7s6UGdVyBcImG6+blWQxCIXHm3C4IM01JvDLwe4M8z7/3z6F+CAgDvLQ/wZUlsl3U4Hrdyc2qgVdRf7lxohW41hhK43GrntCPqvET8rutJusJbvI3X/ngapfyTSSPbLMYtTQ8MDRwtxoa/C8V0e9Ka5vUtyfFjXhNLNU/nkPe4BhsqF/usSQaPwiQKQYg2V/dOBRJ9qn3BE38LONRuVKMy3RcLwozmB6+2uqGpbyGjNMV3MQ7KfhRkYINYl3eic5swHmQVFzT9ciQ0m7JrYBL/WqHrbPEyX/bGTFhu359PhKeFevK5ipOtQ1ZkvGZXa4vw1CGDTM+nwmt1MWm4dCGPCX7HQCAULQyqQXC1ymPaLdz7OL0CmaVK4/zV7C4Oz4WzjBVy/IvX3UCmA4Oi4GoLfBFJyu0rjmVWarGnbFt82FYcR6c+G95Tvrr6e2qNeNorT0wY4g=");
    public static final Skin MONEY = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY0MzkzMzQ1MjE1OSwKICAicHJvZmlsZUlkIiA6ICIyM2YxYTU5ZjQ2OWI0M2RkYmRiNTM3YmZlYzEwNDcxZiIsCiAgInByb2ZpbGVOYW1lIiA6ICIyODA3IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU4MzJmZGMzOGU2YThlMDVlZWMzOTZiYWZiMGE4MWY5MzdhNjE3ZjE1YTc1MDlkODVkYzk4MmRiZjBhYjVhOWYiCiAgICB9CiAgfQp9", "cH8CwRs/IDlgZx9XaTfeiKTE/K4WX444NlK/4H7IIsViUIv0L1mNdH3VCgULUcy05lKg+Kep+a6OUfLPYLYBmvk0UdqjEBCTWumkV4Ljh/jKXXkEihx68o1bum2/1K4LA+SvSuAUocpqbvCA67rzqF1o6tsWDjoFHeNd2p6i9CBOWp8IEY21hN9dHTkY8V/+CN7bKBSe/DsN3d8Mnd8bkLM18WjcRc0TKK+8DimiFyLaybdV/V5csdg+6W12RAetfMRnE4jlMGzNcQJ3bZm8h8tyO3af6ODThrQbJ1BoFZhxSxC5RPOMYmXmGcEwyTLxCdEmI8KRc8xPRiwuC4bNU7vv98HQgTh8gGFRcAjQFfjKwrNBn5CaBb00yhXQeFEJ96pAJE20ylfCXTUVAUnbfCQZBiGvpYyrIrq2/CHnWzSFckVFIsm6bYY7B2onqhWQ5vG13bo2u3Ze9ULtVDpdZlL5xoi2s3UPiwFgkLCLfiB8kMCoJlEyP4hokfKKMfaaNmPeIcdvwlSw/A3EbOeZp3xI10PqnjSwscBgU3K4Gch4m/X9jR1Idu/XAAJX10ekx6cAEZ+izCoNeQrqwgdmdkYeJZ7MBp49I4iI2ZQm822z9WeNWA9JU2CM1LKdQ8PW6HcfijOVPkXBeNkXFvw3qmkeviulc2wVKX4u5jeI2qI=");
    public static final Skin WORKER = new Skin("eyJ0aW1lc3RhbXAiOjE1ODY1Njc2NzAyNzMsInByb2ZpbGVJZCI6ImJlY2RkYjI4YTJjODQ5YjRhOWIwOTIyYTU4MDUxNDIwIiwicHJvZmlsZU5hbWUiOiJTdFR2Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS82OWE2MDBhYjBhODMwOTcwNjViOTVhZTI4NGY4MDU5OTYxNzc0NjA5YWRiM2RiZDNhNGNhMjY5ZDQ0NDA5NTUxIn19fQ==", "MfRt6pmgd9s/zFTX3UQbOObeP1smeWhETCMrdi4s1WF6YOQw2q7L+c0cAQV6Kq7nNenm5ptzZMkwwkIKSzJptaAKdk2vvFOLvxMZbG5YhQhPCkh+krG89GxIE4cUHieHvUb1iA37hUduNWdUIUceL2cmIIsw2Q+20Zu26DxEukFKYfZpWAEADXrTi8/yyCbdIuwX17OpGQeXU9AwcY+Gm6o794X9FShMYcmab5VKzzhr3mhGOhwcSlfVS5ZiECMzCROG/5LG3ondRCDBCh8yx9xiEwOC1u9+jSTO4FGnpcFWpWnUZ0GF+TBpzqV0H05t2zsc4kqsy8nMlnq6NW5WppmAwoEcKaqicUWFNonyMQij2iZGnSTdunzrmRo3gbZmJI9EhYy3X+E9cWPxPoizDYeQbdM7c+WUHxt6JpTe9S85mpLWOEd3F1oKsMyK7dBpJQ0k4S+yo4If8newWJlqLq/V0KfXUrt39dGF2Wh+LpshWVnOYJN2omf0l4SOVafmoYNG5mQ8pVUs3I+Z1HaLe3wEZIVur6hJ6SpBH+2ZprUjSVZnOmKl9w2IHxNiFJiMUsxGsc99Zf29QT2EOVBVgcAkC/LsVnC5qWeXKFG2U0J6cUlqEQUmUuUW8lBvc6hHOBWdhBTDO7PtaGXHJK/Qn1oLMpApUC3qe68f4YSMh3M=");
    public static final Skin MEDAL = new Skin("ewogICJ0aW1lc3RhbXAiIDogMTY3MjY4Mzk5Njc4MiwKICAicHJvZmlsZUlkIiA6ICJjMTNkYzkxZjg1YjA0ZWM4OGU2NDk5YzdjZDc4Zjk3MSIsCiAgInByb2ZpbGVOYW1lIiA6ICJjYXNzdGhlY3J5cHRpZCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS81ODAwMmIyOTllOGQzMjdjZjRjMjFkMjJjNzgyYzFiMjAwMWQyNGZiNmQ4ZDI2MWFmOGQ4Y2EzODYxYmFmOGIyIiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=", "eINuOYYoDGdm1bdYUuq8SkH2Nn7neRyDWTqITEcjmM9GEialakOMpztOA8dA477TzqUXmqmxr2UOwj9LPBSHXiY/BBMNxc93NF1buAAiTVRlrRRztpxS/HZvE0NjVR/hsV69hnGSeEe9Uumg2w4C7SZjB0panjUHtslfyMJFn6ea658EQ3Op9IENmTPz7My7OLfuhDfXgPYYkbuCQS+w+7JTj/kUCkzRVFNuhuIbCAI5o2l5NA9eEMMNxB/dSWbHJb2J6E2I9XG8WecFzriISf7+ujWU5bNodJyzzmrpSwZpfKZhNct7YGaaFHZf79aqAPMV64mXGyzj3qbPsL2sTQ1ER/1l4/b6tpOiyqXvT9ItkfzRBTUv8v9Iq3R6VGDSs0E/y23t+6aib/gbkQo+1j3DlfVoVgT3dfSiFwhkMgq/DfTH1JKhlw3nkC7lQYeBk3amGfixp+IHCrJ0SDeu7jsVpEgCK5FCaF6rTmpl6suIOLtE2AYPN6swiYcSMg8klPEWIKqpCe6tkxWOvFHLQOtz8eUSpqNAoQH1Xx2SwSLvQ7x1d9RWkbibyeADg7j7+SDMXaXlQHLAsSrmlSeWzFrfuptcTgSmWKQsSCMvNmBOuYqLn9FtKbG6h4FJPqstfJNZcboeSXHYtXJqjJZs3K7S8qp2BlvRFGhIDb0rZDc=");

    public static BukkitTask pingHandle;

    public static void updatePlayerList() {
        //TableTabList tab = null;
        Bukkit.getScheduler().runTaskLaterAsynchronously(LibertyCity.INSTANCE, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {

                int row = 2;
                int column = 2;

                TableTabList tab = (TableTabList) LibertyCity.tabInstance.getTabList(player);
                //tab.setBatchEnabled(true);

                // Switch column
                if (row > 18 && column == 2) {
                    column++;
                    row = 0;
                }

                // Player list is full
                if (row > 19 && column == 3) return;

                ServerData server = Data.data.getServerData(Bukkit.getServer());
                for (Player p : Bukkit.getOnlinePlayers()) {
                    tab.set(column, row + 1, new TextTabItem(" ", 0, QUESTION_MARK));
                    if (!server.vanishedPlayers.contains(p)) {
                        tab.set(column, row, new PlayerTabItem(p));
                        row++;
                    }
                }
                for (Player vp : server.vanishedPlayers) {
                    tab.set(column, row + 1, new TextTabItem(" ", 0, QUESTION_MARK));
                    tab.set(column, row, new PlayerTabItem(vp));
                    row++;
                }
                //tab.batchUpdate();
            }
            //tab.setBatchEnabled(false);
        }, 20L);
    }

    public static void updateTablist(Player p) {
        // Tabbed.getTabbed(LibertyCity.getPlugin(LibertyCity.class)).newTableTabList(p);
        // java -jar mc.jar --ip game7.falixserver.net --port 16316 --delay 3000 --logincmd login --registercmd register -t 5 -n bot
        TableTabList tab = Tabbed.getTabbed(LibertyCity.getPlugin(LibertyCity.class)).newTableTabList(p);
        tab.setHeader("§2§LLiberty§a§lCity §4§lRP" + "\n");
        tab.setFooter("\n" + "§7Site Web » §7www.§2liberty§acity§7.§4fr" + "\n" + "§7TeamSpeak » ts.§2liberty§acity§7.§4fr" + "\n" + "§7Discord » discord.§2liberty§acity.§4fr");

        PlayerData data = Data.data.getUserData(p);
        ServerData server = Data.data.getServerData(Bukkit.getServer());
        if(data == null) return;
        String currentChat = "§4§LERROR23";

        // Establish ping stability
        pingHandle = new BukkitRunnable() {

            @Override
            public void run() {
                final int ping = PingUtil.getPing(p);
                Skin signal = QUESTION_MARK;

                if(ping >= 250) signal = SIGNAL_LOST;
                if(ping <= 200) signal = SIGNAL_LOW;
                if(ping <= 150) signal = SIGNAL_MEDIUM;
                if(ping <= 100) signal = SIGNAL_GOOD;
                if(ping <= 50) signal = SIGNAL_HIGH;
                tab.set(0, 8, new TextTabItem(" §7Ping » §a" + ping, ping, signal));
            }

        }.runTaskTimerAsynchronously(LibertyCity.INSTANCE, 20*16, 20*16);


        // Establish server stability
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> {
            final double TPS = LagCalculator.getTPS();
            double TPSPercentage = (Math.round((TPS * 100) / 20) * 100.0) / 100.0;
            String percentageColor = "§0" + TPSPercentage;
            if(TPSPercentage > 100) TPSPercentage = 100;
            if(TPSPercentage >= 97.5) percentageColor = "§2" + TPSPercentage + "%";
            if(TPSPercentage < 97.5) percentageColor = "§a" + TPSPercentage + "%";
            if(TPSPercentage < 90) percentageColor = "§e" + TPSPercentage + "%";
            if(TPSPercentage < 85) percentageColor = "§6" + TPSPercentage + "%";
            if(TPSPercentage < 80) percentageColor = "§c" + TPSPercentage + "%";
            if(TPSPercentage < 75) percentageColor = "§4" + TPSPercentage + "%";
            if(TPSPercentage <= 50) percentageColor = "§0" + TPSPercentage + "%";
            tab.set(0, 7, new TextTabItem(" §7Stabilitée » " + percentageColor, 0, SERVER));
        }, 20, 20);

        // Chat interpreter
        if(data.rpCurrentChat == 0) currentChat = "§3§LHRP";
        if(data.rpCurrentChat == 1) currentChat = "§2§LRP";
        if(data.rpCurrentChat == 2) currentChat = "§b§lPLC";
        if(data.rpCurrentChat == 3) currentChat = "§4§LGNG";

        // Column 0
        tab.set(0, 0, new TextTabItem(StringUtils.center("§2§nServeur§r", 34), 0, FULL_DARK_GREEN));
        tab.set(0, 2, new TextTabItem(" §7IP » §2liberty§acity§7.§4fr", 0, INTERNET));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> {
            int removeCount = 0;
            for(Player vp : server.vanishedPlayers) {
                if(vp.isOnline()) removeCount++;
            }
            tab.set(0, 3, new TextTabItem(" §7Joueurs » §a" + (Bukkit.getOnlinePlayers().size() - removeCount) + "§7/§2" + Bukkit.getMaxPlayers(), 0, Skins.getPlayer("?")));
        }, 20L, 20L);
        tab.set(0, 5, new TextTabItem(StringUtils.center("§2§nInformations§r", 34), 0, FULL_DARK_GREEN));
        // COL 0 | ROW 7 TAKEN
        // COL 0 | ROW 8 TAKEN
        tab.set(0, 10, new TextTabItem(StringUtils.center("§2§nDate & Heure§r", 34), 0, FULL_DARK_GREEN));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(0, 12, new TextTabItem(" §7Heure » §a" + TimeUtil.getTime(), 0, CLOCK)), 20, 20);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(0, 13, new TextTabItem(" §7Date » §a" + TimeUtil.getDate(), 0, DATE_13)), 20, 20);
        tab.set(0, 15, new TextTabItem(StringUtils.center("§2§nCommande d'aide§r", 34), 0, FULL_DARK_GREEN));
        tab.set(0, 17, new TextTabItem(" §a/help", 0, COMMAND_BLOCK));
        tab.set(0, 18, new TextTabItem(" §a/helpop", 0, COMMAND_BLOCK));
        tab.set(0, 19, new TextTabItem(" §a/astuce", 0, COMMAND_BLOCK));

        // Column 1
        tab.set(1, 0, new TextTabItem(StringUtils.center("§3§nIdentitée§r", 34), 0, FULL_CYAN));
        tab.set(1, 2, new TextTabItem(" §7Nom » §2§l" + data.rpNom, 0, INFO));
        tab.set(1, 3, new TextTabItem(" §7Prénom » §a§l" + data.rpPrenom, 0, INFO));
        tab.set(1, 4, new TextTabItem(" §7Âge » §c§l" + data.rpAge + " ans", 0, INFO));
        tab.set(1, 5, new TextTabItem(" §7Pseudo » §4§l" + p.getName(), 0, Skins.getPlayer(p.getName())));
        tab.set(1, 7, new TextTabItem(StringUtils.center("§6§nInformations§r", 34), 0, FULL_GOLD));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () ->  tab.set(1, 9, new TextTabItem(" §7Argent » §6§l$" + MoneyUtils.getBank(p, true), 0, MONEY)), 20, 20);
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(1, 10, new TextTabItem(" §7Travail » " + data.rpCurrentJob, 0, WORKER)), 20, 20);
        tab.set(1, 11, new TextTabItem(" §7Niveau » §8§kAucun", 0, MEDAL));
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> {
            if (data.rpPoliceRank != null) tab.set(2, 11, new TextTabItem(" §7Rang » §b" + data.rpPoliceRank, 0, MEDAL));
            if (data.rpGangRank != null) tab.set(2, 11, new TextTabItem(" §7Gang » §4" + data.rpGangRank, 0, MEDAL));
        }, 20, 20);
        String finalCurrentChat = currentChat;
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> tab.set(1, 12, new TextTabItem(" §7Chat sélectionné » " + finalCurrentChat, 0, CHAT_BUBBLE)), 20, 20);

        // Column 2 & 3
        Bukkit.getScheduler().runTaskTimerAsynchronously(LibertyCity.INSTANCE, () -> {
            int removeCount = 0;
            for(Player vp : server.vanishedPlayers) {
                if(vp.isOnline()) removeCount++;
            }
            tab.set(2, 0, new TextTabItem(StringUtils.center("§2§nJoueurs en ligne§r §7(" + (Bukkit.getOnlinePlayers().size() - removeCount) + ")", 34), 0, FULL_LIME));
        }, 20L, 20L);
        for(int i = 2; i < 20; i++) tab.set(2, i, new TextTabItem(" ", 0, QUESTION_MARK));
        for(int h = 0; h < 20; h++) tab.set(3, h, new TextTabItem(" ", 0, QUESTION_MARK));

    }

}
